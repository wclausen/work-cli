package com.wclausen.work.commands.comment

import com.github.michaelbull.result.*
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.StatefulCommandWorkflow
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueComment
import com.wclausen.work.jira.api.model.JiraComment

class CommentWorkflow(
    private val currentTask: String,
    private val jiraService: JiraService
) : StatefulCommandWorkflow<Unit, CommentWorkflow.State, Result<JiraComment, CommentWorkflow.Error>>() {
    sealed class State {
        class PromptForComment : State()
        class ReceivedComment(val comment: String) : State()
        class Finished(val message: String): State()
    }

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause){
        class JiraCommentFailed(cause: Throwable) : Error("Attempt to update jira comment failed", cause)
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): CommentWorkflow.State {
        return State.PromptForComment()
    }

    override fun render(props: Unit, state: CommentWorkflow.State, context: RenderContext<CommentWorkflow.State, Result<JiraComment, CommentWorkflow.Error>>): Command {
        return when (state) {
            is State.PromptForComment -> Command.Prompt("Enter your comment for $currentTask") { message ->
                context.actionSink.send(action {
                    nextState = State.ReceivedComment(message)
                })
            }
            is State.ReceivedComment -> {
                context.runningWorker(Worker.create<Result<JiraComment, Error>> {
                    emit(runCatching {
                        jiraService.commentOnIssue(currentTask, IssueComment(state.comment))
                    }.mapError { Error.JiraCommentFailed(it) })
                }) {
                    action {
                        nextState = it.mapBoth(
                            { State.Finished("Success")},
                            { State.Finished("Failed")})
                        setOutput(it)
                    }
                }
                Command.Echo("Sending comment to Jira")
            }
            is State.Finished -> Command.Echo(state.message)
        }
    }

    override fun snapshotState(state: CommentWorkflow.State): Snapshot {
        return Snapshot.EMPTY
    }

}
