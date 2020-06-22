package com.wclausen.work.command.commit

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.mapError
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.base.getTaskId
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.command.start.output
import com.wclausen.work.command.start.sendToParent
import com.wclausen.work.git.GitService
import javax.inject.Inject

class CommitWorkflow @Inject constructor(
    private val gitService: GitService
) : CommandOutputWorkflow<WorkState, CommitWorkflow.State, Unit>() {

    companion object {
        const val SUCCESS_MESSAGE = "Successfully committed to git"
        const val NO_TASK_ERROR_MESSAGE = "You must select a task before commenting"
        const val COMMIT_IN_PROGRESS_MESSAGE = "Committing to git"
        const val FAILED_TO_COMMIT_MESSAGE = "Failed to commit to git"

        fun getPromptMessage(currentTask: String) = "Enter your commit message for $currentTask"
    }

    sealed class State {
        class PromptForCommitMessage : State()
        class ReceivedCommitMessage(val message: String) : State()
        sealed class Finished(val message: String) : State() {
            class Success : Finished(SUCCESS_MESSAGE)
            class Error(val error: CommitWorkflow.Error) : Finished(error.explanation)
        }
    }

    sealed class Error(val explanation: String, cause: Throwable) : Throwable(explanation, cause) {
        class InvalidInitialState : Error(NO_TASK_ERROR_MESSAGE, IllegalStateException())
        class CommitFailedError(cause: Throwable) : Error(FAILED_TO_COMMIT_MESSAGE, cause)
    }

    override fun initialState(props: WorkState, snapshot: Snapshot?): State {
        return when (props) {
            WorkState.Uninitialized, WorkState.Waiting -> State.Finished.Error(Error.InvalidInitialState())
            is WorkState.Executing -> State.PromptForCommitMessage()
        }
    }

    override fun render(
        props: WorkState,
        state: State,
        context: RenderContext<State, Output<Unit>>
    ) {
        when (state) {
            is State.PromptForCommitMessage -> {
                context.output(Command.Prompt(getPromptMessage(props.getTaskId())) { message ->
                    context.actionSink.send(action {
                        nextState = State.ReceivedCommitMessage(message)
                    })
                })
            }
            is State.ReceivedCommitMessage -> {
                context.output(Command.Echo(COMMIT_IN_PROGRESS_MESSAGE))
                context.commitToGit(state.message, gitService)
            }
            is State.Finished -> {
                context.output(Command.Echo(state.message))
                context.finish(state)
            }
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }

}

private fun <OutputT : Any> RenderContext<CommitWorkflow.State, OutputT>.commitToGit(
    message: String,
    gitService: GitService
) {
    runningWorker(Worker.create {
        emit(gitService.commitProgress(message)
            .mapError { CommitWorkflow.Error.CommitFailedError(it) })
    }) {
        action {
            nextState = it.mapBoth({ CommitWorkflow.State.Finished.Success() },
                { CommitWorkflow.State.Finished.Error(it) })
        }
    }

}

private fun <StateT> RenderContext<StateT, Output<Unit>>.finish(result: CommitWorkflow.State.Finished) {
    val output = when (result) {
        is CommitWorkflow.State.Finished.Success -> Ok(Unit)
        is CommitWorkflow.State.Finished.Error -> Err(result.error)
    }
    sendToParent(Output.Final(output))
}
