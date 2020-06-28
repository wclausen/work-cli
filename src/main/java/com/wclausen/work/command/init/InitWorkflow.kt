package com.wclausen.work.command.init

import com.github.michaelbull.result.Ok
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.command.start.output
import com.wclausen.work.inject.WorkLogFile
import com.wclausen.work.kotlinext.Do
import java.nio.file.Path
import javax.inject.Inject

class InitWorkflow @Inject constructor(
    private val createConfigWorkflow: CreateConfigWorkflow, @WorkLogFile private val logPath: Path
) : CommandOutputWorkflow<Unit, InitWorkflow.State, WorkState>() {
    sealed class State {
        class CreateConfig : State()
        class CreateWorkUpdateLog : State()
    }

    override fun initialState(props: Unit, snapshot: Snapshot?) = State.CreateConfig()

    override fun render(
        props: Unit, state: State, context: RenderContext<State, Output<WorkState>>
    ) {
        Do exhaustive when (state) {
            is State.CreateConfig -> context.renderChild(createConfigWorkflow, Unit) {
                when (it) {
                    is Output.InProgress, is Output.Log -> sendOutputToParent(it)
                    is Output.Final -> goToState(State.CreateWorkUpdateLog())
                }
            }
            is State.CreateWorkUpdateLog -> {
                context.output(Command.Echo("Creating work log: $logPath"))
                context.runningWorker(Worker.from {
                    val logFile = logPath.toFile()
                    if (!logFile.exists()) {
                        logFile.createNewFile()
                    }
                    Unit
                }) {
                    com.squareup.workflow.action {
                        setOutput(Output.Final(Ok(WorkState.Waiting)))
                    }
                }
            }
        }
    }

    private fun goToState(state: State) = action {
        nextState = state
    }

    private fun <T> sendOutputToParent(output: Output<T>) = action {
        setOutput(output as Output<WorkState>)
    }

    override fun snapshotState(state: State) = Snapshot.EMPTY
}