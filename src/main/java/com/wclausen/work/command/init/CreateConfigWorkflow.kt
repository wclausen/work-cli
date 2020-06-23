package com.wclausen.work.command.init

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.WorkflowAction
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigCreator
import com.wclausen.work.config.JiraConfig
import com.wclausen.work.kotlinext.Do

class CreateConfigWorkflow(
    private val configCreator: ConfigCreator
) : CommandOutputWorkflow<Unit, CreateConfigWorkflow.State, WorkState>() {

    companion object {
        const val GET_USERNAME_PROMPT =
            "Please enter your jira username (e.g. wclausen@dropbox.com)"
        const val GET_JIRA_API_TOKEN_PROMPT = "Please enter your jira api token"
        const val SAVING_CONFIG_MESSAGE = "Saving config file at: "
    }

    sealed class State() {
        object GetJiraUserName : State()
        data class GetJiraPassword(val jiraUsername: String) : State()
        data class SavingConfigFile(val config: Config) : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State.GetJiraUserName
    }

    override fun render(
        props: Unit, state: State, context: RenderContext<State, Output<WorkState>>
    ) {
        Do exhaustive when (state) {
            State.GetJiraUserName -> context.outputPromptForInfo(GET_USERNAME_PROMPT) { username ->
                action {
                    nextState = State.GetJiraPassword(username)
                }
            }
            is State.GetJiraPassword -> context.outputPromptForInfo(GET_JIRA_API_TOKEN_PROMPT) { token ->
                action {
                    nextState =
                        State.SavingConfigFile(Config(JiraConfig(state.jiraUsername, token)))
                }
            }
            is State.SavingConfigFile -> {
                context.saveConfig(state.config)
            }
        }
    }

    private fun RenderContext<State, Output<WorkState>>.outputPromptForInfo(
        promptText: String, onResponse: (String) -> WorkflowAction<State, Output<WorkState>>
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

    private fun RenderContext<State, Output<WorkState>>.saveConfig(
        config: Config
    ) {
        runningWorker(Worker.create {
            emit(Output.InProgress(Command.Echo(SAVING_CONFIG_MESSAGE + configCreator.configLocation)))
            configCreator.createConfigFile(config).mapBoth(success = {
                emit(Output.Final(Ok(config)))
            }, failure = {
                emit(Output.Final(Err(it)))
            })
        }, "save_config") { output ->
            action {
                nextState
            }
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}


