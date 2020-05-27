package com.wclausen.work.command.init

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.action
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.StatefulCommandWorkflow
import com.wclausen.work.config.Config
import com.wclausen.work.config.JiraConfig
import javax.inject.Inject

class InitWorkflow @Inject constructor() : StatefulCommandWorkflow<
        Unit,
        InitWorkflow.InitializationState,
        Result<Config, InitWorkflow.InitializationError>>() {

    companion object {
        const val GET_USERNAME_PROMPT = "Please enter your jira username (e.g. wclausen@dropbox.com)"
        const val GET_JIRA_API_TOKEN_PROMPT = "Please enter your jira api token"
    }

    private var config = Config(JiraConfig("", ""))

    sealed class InitializationState {
        object GetUsername : InitializationState()
        object GetPassword : InitializationState()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): InitializationState {
        return InitializationState.GetUsername
    }

    override fun render(props: Unit, state: InitializationState, context: RenderContext<InitializationState, Result<Config, InitializationError>>): Command {
        return when (state) {
            InitializationState.GetUsername -> Command.Prompt(GET_USERNAME_PROMPT) { jiraUsername ->
                config = config.copy(jira = config.jira.copy(jira_email = jiraUsername))
                context.actionSink.send(action {
                    nextState = InitializationState.GetPassword
                })
            }
            InitializationState.GetPassword -> Command.Prompt(GET_JIRA_API_TOKEN_PROMPT) { jiraApiToken ->
                context.actionSink.send(action {
                    setOutput(Ok(config.copy(
                        jira = config.jira.copy(jira_api_token = jiraApiToken)))) })
            }
        }
    }

    // Not currently used, but here for future error handling/standardization across tasks
    class InitializationError{

    }

    override fun snapshotState(state: InitializationState): Snapshot {
        return Snapshot.EMPTY
    }

}
