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
import com.squareup.workflow.action
import com.wclausen.work.base.WorkflowState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.StatefulCommandWorkflow
import com.wclausen.work.git.GitService
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartWorkingWorkflow(
    private val taskId: String?,
    private val jiraService: JiraService,
    private val gitService: GitService
) : StatefulCommandWorkflow<Unit, StartWorkingWorkflow.StartTaskState, Result<WorkflowState, StartWorkingWorkflow.StartTaskError>>() {

    companion object {
        const val LOADING_TASKS_MESSAGE = "Loading tasks from jira..."

        const val LOADING_TASKS_FAILED_MESSAGE = "Failed when loading jira tasks for user"
        const val TASK_SELECTION_FAILED_MESSAGE = "Selected task is not within bounds"
        const val GIT_BRANCH_CHECKOUT_FAILED_MESSAGE = "Failed to checkout branch for task"
    }

    sealed class StartTaskState {
        class LoadTasks : StartTaskState()
        class TasksLoaded(val tasks: List<IssueBean>) : StartTaskState()
        class InvalidTaskSelected(val tasks: List<IssueBean>) : StartTaskState()
        class TaskSelected(val selectedTask: IssueBean) : StartTaskState()
        class GoalProvided(val selectedTask: IssueBean) : StartTaskState()
        class BranchCheckedOut(val branchName: String) : StartTaskState()
        class Error(val userMessage: String) : StartTaskState()
    }

    sealed class StartTaskError(message: String, throwable: Throwable) : Throwable(message, throwable) {
        class LoadTasksError(throwable: Throwable) : StartTaskError(LOADING_TASKS_FAILED_MESSAGE, throwable)
        class TaskSelectionError(throwable: Throwable) : StartTaskError(TASK_SELECTION_FAILED_MESSAGE, throwable)
        class CheckoutBranchError(cause: GitService.GitError) : StartTaskError(GIT_BRANCH_CHECKOUT_FAILED_MESSAGE, cause) {

        }
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): StartTaskState {
        return StartTaskState.LoadTasks()
    }

    override fun render(props: Unit, state: StartTaskState, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command {
        return when (state) {
            is StartTaskState.LoadTasks -> loadTasks(context)
            is StartTaskState.TasksLoaded -> promptForTaskSelection(state.tasks, context)
            is StartTaskState.InvalidTaskSelected -> promptForTaskSelectionAfterError(state.tasks, context)
            is StartTaskState.TaskSelected -> {
                checkoutGitBranchInBackground(state.selectedTask.key)
                Command.MultipleCommands(listOf(
                    showSelectedTask(state.selectedTask.key),
                    promptForTaskGoal(state.selectedTask, context))
                )
            }
            is StartTaskState.GoalProvided -> {
                checkoutBranch(state.selectedTask.key, context)
                Command.Echo("Checking out branch for task...")
            }
            is StartTaskState.BranchCheckedOut -> Command.Echo("Now on branch: ${state.branchName}")
            is StartTaskState.Error -> Command.Echo(state.userMessage)
        }
    }

    private fun checkoutBranch(taskId: String, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>) {
        context.runningWorker(Worker.create {
            emit(gitService.checkout(taskId))
        }) {
            action {
                nextState = it.mapBoth(
                    {
                        setOutput(Ok(WorkflowState.Executing(taskId)))
                        StartTaskState.BranchCheckedOut(taskId)
                    },
                    {
                        setOutput(Err(StartTaskError.CheckoutBranchError(it)))
                        StartTaskState.Error("Failed to check out branch: $taskId")
                    }
                )
            }
        }
    }

    private fun promptForTaskGoal(selectedTask: IssueBean, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command {
        return Command.Prompt("Describe the finished state of the task") {
            context.actionSink.send(action {
                nextState = StartTaskState.GoalProvided(selectedTask)
            })
        }
    }

    private fun checkoutGitBranchInBackground(taskId: String) {
        CoroutineScope(Dispatchers.Default).launch {
            gitService.checkout(taskId)
        }
    }

    private fun showSelectedTask(selectedTaskId: String): Command =
        Command.Echo("Selected task: $selectedTaskId")

    private fun promptForTaskSelectionAfterError(tasks: List<IssueBean>, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command {
        return Command.MultipleCommands(listOf(
            Command.Echo("Your selection was invalid. Please select within [${1..tasks.size}]"),
            getTaskSelectionPromptCommand(tasks, context)
        ))
    }

    private fun promptForTaskSelection(tasks: List<IssueBean>, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command {
        return Command.MultipleCommands(listOf(
            Command.Echo("Your current tasks are:\n" + tasks.toSummaries().joinToString("\n")),
            getTaskSelectionPromptCommand(tasks, context)
        ))
    }

    private fun getTaskSelectionPromptCommand(tasks: List<IssueBean>, context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command.Prompt =
        Command.Prompt("Please select a task") {
            context.actionSink.send(action {
                nextState = validateSelection(it, 1..tasks.size)
                    .mapBoth(
                        { StartTaskState.TaskSelected(tasks[it - 1]) },
                        { StartTaskState.InvalidTaskSelected(tasks) }
                    )
            }
            )
        }

    private fun validateSelection(selectedTaskString: String, selectionRange: IntRange): Result<Int, StartTaskError> = runCatching {
        val selectedTaskIdx = selectedTaskString.toInt()
        if (selectionRange.contains(selectedTaskIdx)) {
            selectedTaskIdx
        } else {
            throw IllegalArgumentException("Selection of task must be within bounds")
        }
    }.mapError { StartTaskError.TaskSelectionError(it) }

    private fun loadTasks(context: RenderContext<StartTaskState, Result<WorkflowState, StartTaskError>>): Command {
        context.runningWorker(Worker.create {
            val taskSummaries = getTasksFromJira()
                .map { it.issues }
            emit(taskSummaries)
        }) {
            action {
                it.mapBoth(
                    {
                        nextState = StartTaskState.TasksLoaded(it)
                    },
                    {
                        nextState = StartTaskState.Error(it.message!!)
                        setOutput(Err(it))
                    }
                )
            }

        }
        return Command.Echo(LOADING_TASKS_MESSAGE)
    }

    private suspend fun getTasksFromJira() = runCatching {
        // TODO: this won't return anything until I read current user email from config
        jiraService.getTasksForCurrentUser()
    }.mapError { StartTaskError.LoadTasksError(it) }


    override fun snapshotState(state: StartTaskState): Snapshot {
        return Snapshot.EMPTY
    }

}

private fun List<IssueBean>.toSummaries() =
    mapIndexed { idx, issue ->
        "\t" + (idx + 1).toString() + ". " + issue.fields.summary
    }