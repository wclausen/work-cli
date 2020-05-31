package com.wclausen.work.command.init

import com.github.michaelbull.result.Ok
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.StatefulWorkflow
import com.squareup.workflow.Worker
import com.squareup.workflow.WorkflowAction
import com.squareup.workflow.action
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigCreator
import com.wclausen.work.config.JiraConfig
import com.wclausen.work.kotlinext.Do

class InitWorkflow : CommandOutputWorkflow<Unit, InitWorkflow.State>() {

    companion object {
        const val GET_USERNAME_PROMPT = "Please enter your jira username (e.g. wclausen@dropbox.com)"
        const val GET_JIRA_API_TOKEN_PROMPT = "Please enter your jira api token"
    }

    sealed class State() {
        object GetJiraUserName : State()
        data class GetJiraPassword(val jiraUsername: String) : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State.GetJiraUserName
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Output>) {
        Do exhaustive when (state) {
            State.GetJiraUserName -> context.outputPromptForInfo(GET_USERNAME_PROMPT) { username ->
                action {
                    nextState = State.GetJiraPassword(username)
                }
            }
            is State.GetJiraPassword -> context.outputPromptForInfo(GET_JIRA_API_TOKEN_PROMPT) { token ->
                action {
                    setOutput(Output.Final(Ok(Config(JiraConfig(state.jiraUsername, token)))))
                }
            }
        }
    }

    private fun RenderContext<State, Output>.outputPromptForInfo(
        promptText: String,
        onResponse: (String) -> WorkflowAction<State, Output>
    ) {
        runningWorker(Worker.from {
            Command.Prompt(promptText) { response ->
                this.actionSink.send(onResponse.invoke(response))
            }
        }, promptText) {
            action {
                setOutput(Output.InProgress(it))
            }
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}