package com.wclausen.work.fake

import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.Worker
import com.squareup.workflow.action
import com.wclausen.work.base.WorkState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output

class FakeCommandWorkflow : CommandOutputWorkflow<WorkState, Unit, Unit>() {
    override fun initialState(props: WorkState, snapshot: Snapshot?) {
    }

    override fun render(props: WorkState, state: Unit, context: RenderContext<Unit, Output<Unit>>) {
        context.runningWorker(Worker.from {
            Unit
        }) {
            action {
                setOutput(Output.InProgress(Command.Echo("Running command...")))
            }
        }
    }

    override fun snapshotState(state: Unit): Snapshot {
        return Snapshot.EMPTY
    }
}