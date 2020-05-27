package com.wclausen.work.command.commit

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.mapError
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.StatefulCommandWorkflow
import com.wclausen.work.git.GitService
import org.eclipse.jgit.revwalk.RevCommit

class CommitWorkflow(
    private val currentTask: String,
    private val gitService: GitService
) : StatefulCommandWorkflow<Unit, CommitWorkflow.State, Result<RevCommit, CommitWorkflow.Error>>(){
    sealed class State {
        class PromptForCommitMessage : State()
        class ReceivedCommitMessage(val message: String) : State()
        class Finished(val message: String): State()
    }

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause){
        class CommitFailedError(cause: Throwable) : Error("Failed to commit to git", cause)
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State.PromptForCommitMessage()
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Result<RevCommit, Error>>): Command {
        return when (state) {
            is State.PromptForCommitMessage -> Command.Prompt("Enter your commit message for $currentTask") { message ->
                context.actionSink.send(action {
                    nextState = State.ReceivedCommitMessage(message)
                })
            }
            is State.ReceivedCommitMessage -> {
                context.runningWorker(Worker.create {
                    emit(com.github.michaelbull.result.runCatching {
                        gitService.commitProgress(state.message)
                    }.mapError { Error.CommitFailedError(it) })
                }) {
                    action {
                        nextState = it.mapBoth(
                            { State.Finished("Success")},
                            { State.Finished("Failed")})
                        setOutput(it)
                    }
                }
                Command.Echo("Committing to git")
            }
            is State.Finished -> Command.Echo(state.message)
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }

}
