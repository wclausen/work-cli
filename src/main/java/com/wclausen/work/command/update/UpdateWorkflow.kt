package com.wclausen.work.command.update

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.action
import com.wclausen.work.base.WorkflowState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.StatefulCommandWorkflow
import com.wclausen.work.git.GitService
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueComment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class UpdateWorkflow(
    private val issueId: String,
    private val jiraService: JiraService,
    private val gitService: GitService
) : StatefulCommandWorkflow<Unit, UpdateWorkflow.State, Result<WorkflowState, UpdateWorkflow.Error>>() {

    private var responsesCounter = AtomicInteger(0)

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause) {
        class JiraFailedToUpdate(cause: Throwable) : Error("Failed when trying to update jira issue with comment", cause)
        class GitCommitFailed(cause: Throwable) : Error("Failed when committing git files, check your git repo status", cause)
    }

    sealed class State {
        class GetJiraComment : State()
        class GetGitCommit(val jiraComment: String) : State()
        class Waiting(val commitMsg: String) : State()
        class GitFinishedUpdating() : State()
        class JiraFinishedUpdating() : State()
        class Error(val message: String) : State()
        class FinishedBackgroundWork(val message: String) : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State.GetJiraComment()
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Result<WorkflowState, Error>>): Command {
        return when (state) {
            is State.GetJiraComment -> {
                Command.Prompt("Please enter a jira comment") {
                    context.actionSink.send(action {
                        nextState = State.GetGitCommit(it)
                    })
                }
            }
            is State.GetGitCommit -> {
                sendCommentToJira(state.jiraComment, context)
                Command.MultipleCommands(listOf(
                    Command.Echo("Sending jira comment"),
                    Command.Prompt("Please enter a commit message") {commitMsg ->
                        context.actionSink.send(action {
                            nextState = State.Waiting(commitMsg)
                        })
                    }
                ))
            }
            is State.Waiting -> {
                commit(state.commitMsg, context)
                Command.Echo("Committing to git")
            }
            is State.GitFinishedUpdating -> Command.Echo("completed git commit")
            is State.JiraFinishedUpdating -> Command.Echo("Comment added to jira")
            is State.Error -> Command.Echo(state.message)
            is State.FinishedBackgroundWork -> Command.Echo(state.message)

        }
    }

    private fun commit(message: String, context: RenderContext<State, Result<WorkflowState, Error>>) {
        CoroutineScope(Dispatchers.IO).launch {
            gitService.commitProgress(message)
            context.actionSink.send(action {
                responsesCounter.getAndIncrement()
                nextState = if (responsesCounter.get() == 2) {
                    setOutput(Ok(WorkflowState.Executing(issueId)))
                    State.FinishedBackgroundWork("Commit finished")
                } else {
                    State.GitFinishedUpdating()
                }
            })
        }
    }

    private fun sendCommentToJira(comment: String, context: RenderContext<State, Result<WorkflowState, Error>>) {
        CoroutineScope(Dispatchers.IO).launch {
            jiraService.commentOnIssue(issueId, IssueComment(comment))
            context.actionSink.send(action {
                responsesCounter.getAndIncrement()
                nextState = if (responsesCounter.get() == 2) {
                    setOutput(Ok(WorkflowState.Executing(issueId)))
                    State.FinishedBackgroundWork("Jira finished")
                } else {
                    State.JiraFinishedUpdating()
                }
            })
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }

}
