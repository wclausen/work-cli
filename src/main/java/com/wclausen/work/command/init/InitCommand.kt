package com.wclausen.work.command.init

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.CommandOutputWorkflowRunner
import com.wclausen.work.command.base.MainWorkflow
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * Command to initialize CLI tool.
 *
 * Usage: $ work init
 *
 * Prompts for jira credentials (email/api token) and git directory info
 */
class InitCommand : CliktCommand(name = "init"){
    override fun run() {
        CommandOutputWorkflowRunner(
            ConflatedBroadcastChannel(Unit),
            MainWorkflow(InitWorkflow())
        ).run()
    }
}