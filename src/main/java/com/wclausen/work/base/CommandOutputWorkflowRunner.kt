package com.wclausen.work.base

import com.github.ajalt.clikt.output.TermUi
import com.squareup.workflow.launchWorkflowIn
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandOutputWorkflow
import com.wclausen.work.command.base.Output
import com.wclausen.work.kotlinext.Do
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
abstract class CommandOutputWorkflowRunner<in PropsT, StateT, OutputT> constructor(
    private val inputs: BroadcastChannel<PropsT>,
    private val commandWorkflow: CommandOutputWorkflow<PropsT, StateT, OutputT>
) {

    @FlowPreview
    fun run() = runBlocking {
        val workJob = Job()
        val result = launchWorkflowIn(
            CoroutineScope(workJob),
            commandWorkflow,
            inputs.asFlow(),
            null)
        { session ->
            val outputs = session.outputs
                .produceIn(this)

            launch {
                while (true) {
                    val output = outputs.receive()
                    Do exhaustive when (output) {
                        is Output.InProgress -> handleCommand(output.command)
                        is Output.Final<*> -> Unit // do nothing, handled later
                        is Output.Log -> Unit // Do nothing, handled by MainWorkflow
                    }
                }
            }

            return@launchWorkflowIn async { session.outputs.filter { it is Output.Final<*> }.first() }
        }

        val done = result.await()
        workJob.cancelChildren()
        done
    }

    private fun handleCommand(rendering: Command) {
        Do exhaustive when (rendering) {
            is Command.Prompt -> {
                val result = TermUi.prompt(rendering.prompt)!!
                rendering.nextAction(result)
            }
            is Command.Echo -> TermUi.echo(rendering.output)
            is Command.ExecuteCommand ->
                WrapperCommand(rendering.onResult)
                    .main(rendering.args)
            is Command.MultipleCommands ->
                rendering.commands.forEach {
                    handleCommand(it)
                }
        }
    }
}
