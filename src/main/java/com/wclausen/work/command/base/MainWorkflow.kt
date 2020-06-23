package com.wclausen.work.command.base

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.base.WorkStateManager
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.kotlinext.Do

class MainWorkflow<CommandT : CommandOutputWorkflow<WorkState, *, *>>(
    private val workStateManager: WorkStateManager,
    private val createConfigWorkflow: CreateConfigWorkflow,
    private val commandWorkflow: CommandT
) : CommandOutputWorkflow<Unit, MainWorkflow.State, ExitCode>() {
    sealed class State {
        object Startup : State()
        class RunningCommand(val workState: WorkState) : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return workStateManager.getWorkflowState().mapBoth(success = { workState ->
            State.RunningCommand(workState)
        }, failure = {
            return when (it) {
                is WorkStateManager.Error.ConfigError, is WorkStateManager.Error.NoLogFileError -> State.Startup
                is WorkStateManager.Error.WorkUpdateWriteFailed -> throw IllegalStateException("Received unexpected error when executing command: $it")
                is WorkStateManager.Error.EmptyLogFileError, is WorkStateManager.Error.MalformedLogError -> State.RunningCommand(
                    WorkState.Waiting
                )
            }
        })
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Output<ExitCode>>) {
        Do exhaustive when (state) {
            State.Startup -> context.renderChild(createConfigWorkflow, Unit) { output ->
                when (output) {
                    is Output.InProgress -> continueInitialization(output)
                    is Output.Final -> runCommand(output)
                }
            }
            is State.RunningCommand -> context.renderChild(
                commandWorkflow, state.workState
            ) { output ->
                when (output) {
                    is Output.InProgress -> continueCommand(output)
                    is Output.Final -> finish(output)
                }
            }
        }
    }

    private fun finish(output: Output.Final<*>) = action {
        output.result.mapBoth({
            setOutput(Output.Final(Ok(ExitCode(0))))
        }, { error ->
            setOutput(Output.Final(Err(error)))
        })
    }

    private fun runCommand(output: Output.Final<WorkState>) = action {
        output.result.mapBoth({ config ->
            nextState = State.RunningCommand(config)
        }, { error ->
            setOutput(Output.Final(Err(error)))
        })
    }

    private fun continueCommand(output: Output.InProgress) = justPassOutputAlong(output)

    private fun continueInitialization(output: Output.InProgress) = justPassOutputAlong(output)

    // When a child workflow emits an in-progress output, we simply notify our parent and let the child keep working
    private fun justPassOutputAlong(output: Output.InProgress) = action {
        setOutput(output)
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}

data class ExitCode(val code: Int)

