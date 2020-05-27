package com.wclausen.work.command.base

import com.wclausen.work.config.Config

/**
 * Class that encapsulates types of commands that are issued by [CommandWorkflow] instances to be executed by the terminal
 */
sealed class Command {

    data class Prompt(val prompt: String, val nextAction: ((String) -> Unit) = {}) : Command()

    data class Echo(val output: String) : Command()

    data class ExecuteCommand(
        val args: Array<String>,
        val config: Config,
        val onResult: ((Int) -> Unit)?) : Command() {
    }

    data class MultipleCommands(val commands: List<Command>) : Command()

}
