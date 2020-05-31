package com.wclausen.work.command.base

import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.action
import com.wclausen.work.command.init.InitWorkflow
import com.wclausen.work.kotlinext.Do

class MainWorkflow(private val initWorkflow: InitWorkflow) : CommandOutputWorkflow<Unit, MainWorkflow.State>() {
    sealed class State {
        object Uninitialized : State()
        object RunningCommand : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?): State {
        return State.Uninitialized
    }

    override fun render(props: Unit, state: State, context: RenderContext<State, Output>): Unit {
        Do exhaustive when (state) {
            State.Uninitialized -> context.renderChild(initWorkflow, Unit) {
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
                println("Running Command...")
        }
    }

    override fun snapshotState(state: State): Snapshot {
        return Snapshot.EMPTY
    }
}