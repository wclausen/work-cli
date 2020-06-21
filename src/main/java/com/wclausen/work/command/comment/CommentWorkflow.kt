package com.wclausen.work.commands.comment

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.WorkflowAction
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.command.start.output
import com.wclausen.work.command.start.sendToParent
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueComment
import com.wclausen.work.jira.api.model.JiraComment
import com.wclausen.work.kotlinext.Do
import javax.inject.Inject

class CommentWorkflow @Inject constructor(
    private val jiraService: JiraService
) : CommandOutputWorkflow<WorkState, CommentWorkflow.State, Unit>() {

    companion object {
        const val NO_TASK_ERROR_MESSAGE = "You must select a task before commenting"
        const val COMMENT_PROMPT = "Enter your comment"
        const val SENDING_COMMENT_TO_JIRA_MESSAGE = "Sending comment to Jira"
        const val SUCCESS_MESSAGE = "Successfully added comment"
        const val FAILED_TO_UPDATE_JIRA = "Attempt to update jira comment failed"
    }

    sealed class State {
        class PromptForComment : State()
        class ReceivedComment(val comment: String) : State()
        sealed class Finished(val message: String) : State() {
            class Success : Finished(SUCCESS_MESSAGE)
            class Error(val error: CommentWorkflow.Error) : Finished(error.message!!)
        }
    }

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause) {
        class JiraCommentFailed(cause: Throwable) : Error(FAILED_TO_UPDATE_JIRA, cause)

        class InvalidInitialState() : Error(NO_TASK_ERROR_MESSAGE, IllegalStateException())
    }

    override fun initialState(props: WorkState, snapshot: Snapshot?): State {
        return when (props) {
            WorkState.Uninitialized, WorkState.Waiting -> {
                State.Finished.Error(Error.InvalidInitialState())
            }
            is WorkState.Executing -> State.PromptForComment()
        }
    }

    override fun render(
        props: WorkState, state: State, context: RenderContext<State, Output<Unit>>
    ) {
        Do exhaustive when (state) {
            is State.PromptForComment -> context.output(Command.Prompt(COMMENT_PROMPT) { message ->
                context.actionSink.send(action {
                    nextState = State.ReceivedComment(message)
                })
            })
            is State.ReceivedComment -> {
                context.output(Command.Echo(SENDING_COMMENT_TO_JIRA_MESSAGE))
                context.sendCommentToJira(props.getTaskId(), state.comment, jiraService) {
                    it.goesToNextState({
                        State.Finished.Success()
                    })
                }
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

    private fun <StateT, OutputT : Any> RenderContext<StateT, OutputT>.sendCommentToJira(
        taskId: String,
        comment: String,
        jiraService: JiraService,
        andThen: (Result<JiraComment, Error>) -> WorkflowAction<StateT, OutputT>
    ) {
        runningWorker(Worker.from {
            runCatching {
                jiraService.commentOnIssue(taskId, IssueComment(comment))
            }.mapError { Error.JiraCommentFailed(it) }
        }) {
            andThen.invoke(it)
        }
    }

}

private fun <V, E : CommentWorkflow.Error> Result<V, E>.goesToNextState(
    successState: (V) -> CommentWorkflow.State, failureState: (E) -> CommentWorkflow.State = {
        CommentWorkflow.State.Finished.Error(it)
    }
) = action<CommentWorkflow.State, Output<Unit>> {
    nextState = mapBoth(success = {
        successState.invoke(it)
    }, failure = {
        failureState.invoke(it)
    })
}

fun <StateT> RenderContext<StateT, Output<Unit>>.finish(result: CommentWorkflow.State.Finished) {
    val output = when (result) {
        is CommentWorkflow.State.Finished.Success -> Ok(Unit)
        is CommentWorkflow.State.Finished.Error -> Err(result.error)
    }
    sendToParent(Output.Final(output))
}

private fun WorkState.getTaskId(): String = (this as WorkState.Executing).taskId
