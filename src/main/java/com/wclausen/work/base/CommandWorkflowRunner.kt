package com.wclausen.work.base

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.getOrElse
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.runCatching
import com.squareup.workflow.launchWorkflowIn
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.CommandWorkflow
import com.wclausen.work.kotlinext.Do
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.selectUnbiased
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CommandWorkflowRunner<in PropsT, OutputT: Result<*, *>>
@Inject constructor(
    private val inputs: BroadcastChannel<PropsT>,
    private val commandWorkflow: CommandWorkflow<PropsT, OutputT>
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
            val renderings = session.renderingsAndSnapshots
                .map { it.rendering }
                .produceIn(this)
            launch {
                while (true) {
                    val rendering = selectUnbiased<Command> {
                        renderings.onReceive { it }
                    }

                    handleCommand(rendering)
                }
            }

            return@launchWorkflowIn async { session.outputs.first() }
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

class WrapperCommand(private val onResult: ((Int) -> Unit)?) : CliktCommand() {

    val command by option(help = "?").required()
    val arguments by argument().multiple()

    override fun run() {
        val cmd = (listOf(command) + arguments).joinToString(" ")
        val executionResult = startProcess(cmd)
            .andThen(::printResults)
            .andThen(::getExitCode)
        println(executionResult.mapBoth({ "Success"}, { it.printStackTrace() }))
        onResult?.invoke(executionResult.getOrElse { 1 })
    }

    private fun getExitCode(process: Process) = runCatching {
        process.waitFor()
    }


    private fun printResults(process: Process) = runCatching {
        println(process.inputStream.bufferedReader().readText())
        process
    }

    private fun startProcess(cmd: String) = runCatching {
        Runtime.getRuntime().exec(cmd)
    }

}
