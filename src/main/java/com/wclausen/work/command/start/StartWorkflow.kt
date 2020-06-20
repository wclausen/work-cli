package com.wclausen.work.command.start

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.WorkflowAction
import com.squareup.workflow.action
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.config.Config
import com.wclausen.work.git.GitService
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueBean
import com.wclausen.work.kotlinext.Do
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.eclipse.jgit.lib.Ref
import java.util.UUID
import javax.inject.Inject

typealias RenderingContext = RenderContext<StartWorkflow.State, Output<Unit>>

class StartWorkflow @Inject constructor(
    private val jiraService: JiraService, private val gitService: GitService
) : CommandOutputWorkflow<Config, StartWorkflow.State, Unit>() {

    companion object {
        const val LOADING_TASKS_MESSAGE = "Loading tasks from jira..."

        const val LOADING_TASKS_FAILED_MESSAGE = "Failed when loading jira tasks for user"
        const val TASK_SELECTION_FAILED_MESSAGE = "Selected task is not within bounds"
        const val GIT_BRANCH_CHECKOUT_FAILED_MESSAGE = "Failed to checkout branch for task"
    }

    sealed class State {
        class NoTasks : State()
        sealed class TaskSelectionNeeded(val tasks: List<IssueBean>, val workerKey: String = "") :
            State() {
            class FirstTime(tasks: List<IssueBean>) : TaskSelectionNeeded(tasks)
            class AfterInvalidSelection(tasks: List<IssueBean>) : TaskSelectionNeeded(
                tasks, UUID.randomUUID().toString()
            )
        }

        class TaskSelected(val selectedTask: IssueBean) : State()
        class Success(val branchRef: Ref) : State()
        data class Error(val error: StartWorkflow.Error) : State()
    }

    sealed class Error(message: String, throwable: Throwable) : Throwable(message, throwable) {
        class LoadTasksError(throwable: Throwable) : Error(
            LOADING_TASKS_FAILED_MESSAGE, throwable
        )

        class TaskSelectionError(throwable: Throwable) : Error(
            TASK_SELECTION_FAILED_MESSAGE, throwable
        )

        class CheckoutBranchError(cause: GitService.GitError) : Error(
            GIT_BRANCH_CHECKOUT_FAILED_MESSAGE, cause
        )
    }

    override fun initialState(props: Config, snapshot: Snapshot?): State {
        return State.NoTasks()
    }

    override fun render(props: Config, state: State, context: RenderingContext) {
        Do exhaustive when (state) {
            is State.NoTasks -> {
                context.output(loadingTasksMessage())
                context.loadTasks { result ->
                    result.goesToNextState({ State.TaskSelectionNeeded.FirstTime(it.sortedBy { it.key }) })
                }
            }
            is State.TaskSelectionNeeded -> {
                context.output(
                    taskSelectionInfoMessage(state), workerKey = state.workerKey + "info"
                )
                context.output(getTaskSelectionPromptCommand(state.tasks, context) { result ->
                    result.goesToNextState({ State.TaskSelected(it) },
                        { State.TaskSelectionNeeded.AfterInvalidSelection(state.tasks) })
                }, workerKey = state.workerKey)
            }
            is State.TaskSelected -> {
                promptForGoalAndCheckoutBranch(context, state) {
                    it.mapError {
                        Error.CheckoutBranchError(GitService.GitError.CheckoutFailedError(it))
                    }.goesToNextState({
                        State.Success(branchRef = it.first)
                    })
                }
            }
            is State.Success -> {
                context.output(savedGoalMessage())
                context.output(checkedOutBranchMessage(state.branchRef))
                context.finish()
            }
            is State.Error -> {
                context.output(Command.Echo(state.error.message!!))
                context.finish()
            }
        }
    }

    private fun savedGoalMessage() = Command.Echo("Saved goal")

    private fun checkedOutBranchMessage(branchRef: Ref) =
        Command.Echo("Checked out branch: ${branchRef.name}")

    //region [State.NoTasks] functions
    private fun loadingTasksMessage() = Command.Echo("Loading jira tasks...")

    private suspend fun getTasksFromJira() = runCatching {
        // TODO: this won't return anything until I read current user email from config
        jiraService.getTasksForCurrentUser()
    }.mapError { Error.LoadTasksError(it) }

    private fun RenderingContext.loadTasks(andThen: (Result<List<IssueBean>, Error>) -> WorkflowAction<State, Output<Unit>>) {
        runningWorker(Worker.create {
            val taskSummaries = getTasksFromJira().map { it.issues }
            emit(taskSummaries)
        }, "load_tasks") {
            andThen.invoke(it)
        }
    }
    //endregion

    private fun taskSelectionInfoMessage(state: State.TaskSelectionNeeded): Command.Echo {
        return when (state) {
            is State.TaskSelectionNeeded.FirstTime -> showTasks(state.tasks)
            is State.TaskSelectionNeeded.AfterInvalidSelection -> invalidSelectionMessage(state.tasks)
        }
    }

    private fun showTasks(tasks: List<IssueBean>) = Command.Echo(formattedTaskList(tasks))

    private fun invalidSelectionMessage(tasks: List<IssueBean>) =
        Command.Echo("Your selection was invalid. Please select within [${1..tasks.size}]")

    private fun getTaskSelectionPromptCommand(
        tasks: List<IssueBean>,
        context: RenderingContext,
        nextAction: (Result<IssueBean, Error>) -> WorkflowAction<State, Output<Unit>>
    ): Command {
        return Command.Prompt("Please select a task") {
            context.actionSink.send(
                nextAction.invoke(validateSelection(
                    it, 1..tasks.size
                ).map { tasks[it - 1] })
            )
        }
    }

    private fun validateSelection(
        selectedTaskString: String, selectionRange: IntRange
    ): Result<Int, Error> = runCatching {
        val selectedTaskIdx = selectedTaskString.toInt()
        if (selectionRange.contains(selectedTaskIdx)) {
            selectedTaskIdx
        } else {
            throw IllegalArgumentException("Selection of task must be within bounds")
        }
    }.mapError { Error.TaskSelectionError(it) }

    private fun promptForGoalAndCheckoutBranch(
        context: RenderingContext,
        state: State.TaskSelected,
        nextAction: (Result<Pair<Ref, String>, Throwable>) -> WorkflowAction<State, Output<Unit>>
    ) {
        context.runningWorker(Worker.create {
            val gitResult = CoroutineScope(Dispatchers.IO).async {
                gitService.checkout(state.selectedTask.key)
            }
            var goal = ""
            emit(Output.InProgress(promptForGoalCommands(state, onResult = {
                saveGoal(it)
                goal = it
            })))
            val result = gitResult.await().map { it to goal }
            emit(Output.Final(result))
        }) {
            when (it) {
                is Output.InProgress -> sendToParent(it)
                is Output.Final -> nextAction.invoke(it.result)
            }
        }
    }

    private fun saveGoal(goal: String) {

    }

    private fun promptForGoalCommands(
        state: State.TaskSelected, onResult: (String) -> Unit
    ): Command.MultipleCommands {
        return Command.MultipleCommands(
            listOf(
                showSelectedTask(state.selectedTask.key),
                promptForTaskGoal(state.selectedTask, onResult)
            )
        )
    }


    private fun showSelectedTask(selectedTaskId: String): Command =
        Command.Echo("Selected task: $selectedTaskId")

    private fun promptForTaskGoal(selectedTask: IssueBean, onResult: (String) -> Unit): Command {
        return Command.Prompt("Describe the finished state of the task", onResult)
    }

    private fun finish(it: Error.LoadTasksError) = action {
        setOutput(Output.Final(Err(it)))
    }

    private fun showError() = action {
        setOutput(Output.InProgress(Command.Echo("Failed with error")))
    }

//    private fun RenderingContext.checkoutGitBranchInBackground(selectedTask: IssueBean) {
//        runningWorker(Worker.from {
//            gitService.checkout(selectedTask.key)
//        }) {
//            action {
//                nextState = State.BranchCheckedOut(nextState, selectedTask)
//            }
//        }
//    }

    private fun <T> sendToParent(output: Output<T>) = action {
        Do exhaustive when (output) {
            is Output.InProgress -> setOutput(output)
            is Output.Final -> {
                output.result.mapBoth({
                    setOutput(Output.Final(Ok(Unit)))
                }, {
                    setOutput(Output.Final(Err(it)))
                })
            }
        }
    }

    override fun snapshotState(state: State) = Snapshot.EMPTY

    private fun <V, E : Error> Result<V, E>.goesToNextState(
        successState: (V) -> State, failureState: (E) -> State = {
            State.Error(it)
        }
    ) = action {
        nextState = mapBoth(success = {
            successState.invoke(it)
        }, failure = {
            failureState.invoke(it)
        })
    }
}

private fun RenderingContext.finish() {
    sendToParent(Output.Final(Ok(Unit)))
}

fun RenderingContext.sendToParent(output: Output.Final<Unit>) {
    runningWorker(Worker.from { output }) {
        action {
            setOutput(it)
        }
    }
}

private fun <StateT> RenderContext<StateT, Output<Unit>>.output(
    command: Command, workerKey: String = command.toString()
) {
    runningWorker(Worker.from {
        Output.InProgress(command)
    }, workerKey) {
        action {
            setOutput(it)
        }
    }
}

private fun List<IssueBean>.toSummaries() = mapIndexed { idx, issue ->
    "\t" + issue.key + ": " + issue.fields.summary
}

fun formattedTaskList(tasks: List<IssueBean>) =
    "Your current tasks are:\n" + tasks.toSummaries().joinToString("\n")