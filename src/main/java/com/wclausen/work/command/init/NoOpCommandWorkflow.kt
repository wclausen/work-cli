package com.wclausen.work.command.init

import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.wclausen.work.base.WorkState
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output

/**
 * Workflow that does nothing.
 *
 * This class exists for use by the InitCommand workflow runner. The runner performs initialization checks
 * automatically, so the init command itself doesn't need to do any additional work, hence a NoOp command.
 */
class NoOpCommandWorkflow : CommandOutputWorkflow<WorkState, Unit, Unit>() {
    override fun initialState(props: WorkState, snapshot: Snapshot?) = Unit

    override fun render(props: WorkState, state: Unit, context: RenderContext<Unit, Output<Unit>>) =
        Unit

    override fun snapshotState(state: Unit) = Snapshot.EMPTY
}