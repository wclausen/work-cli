package com.wclausen.work.base

import com.wclausen.work.command.base.ExitCode
import com.wclausen.work.command.base.MainWorkflow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * Class that runs Command workflows that issue commands through the Workflow's OutputT.
 *
 * This contrasts with [CommandWorkflowRunner] where running Workflows issue commands through
 * RenderingT
 *
 * The reason for adding this class and changing the way Commands are issued is that it *should*
 * make it easier to compose workflows if they issue Commands through outputs rather than
 * renderings. This goes back to the fact that the UI framework for the application (the terminal)
 * is not declarative, which breaks the normal expectations of the render loop.
 *
 */
@ExperimentalCoroutinesApi
class MainCommandOutputWorkflowRunner constructor(
    mainWorkflow: MainWorkflow<*>
) : CommandOutputWorkflowRunner<Unit, MainWorkflow.State, ExitCode>(
    ConflatedBroadcastChannel(Unit), mainWorkflow
)
