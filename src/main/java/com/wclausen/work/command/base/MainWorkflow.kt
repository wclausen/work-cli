package com.wclausen.work.command.base

import com.github.michaelbull.result.mapBoth
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.config.ConfigReader
import com.wclausen.work.kotlinext.Do

class MainWorkflow(
    private val configReader: ConfigReader,
    private val createConfigWorkflow: CreateConfigWorkflow) : CommandOutputWorkflow<Unit, MainWorkflow.State>() {
    sealed class State {
        object Uninitialized : State()
        object RunningCommand : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return configReader.getConfig()
            .mapBoth(
                success = {
                    State.RunningCommand }
                , failure = {
                    State.Uninitialized })
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Output>): Unit {
        Do exhaustive when (state) {
            State.Uninitialized -> context.renderChild(createConfigWorkflow, Unit) {
                action {
                    Do exhaustive when (it) {
                        is Output.InProgress -> {
                            setOutput(it)
                        }
                        is Output.Final<*> -> {
                            nextState =
                                State.RunningCommand
                        }
                    }
                }
            }
            State.RunningCommand ->
                context.showRunningCommand()
        }
    }

    private fun RenderContext<State, Output>.showRunningCommand() {
        runningWorker(Worker.from { Command.Echo("Running command...") }) {
            action {
                setOutput(Output.InProgress(it))
            }
        }

    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}

