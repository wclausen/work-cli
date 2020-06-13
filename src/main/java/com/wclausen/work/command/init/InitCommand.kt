package com.wclausen.work.command.init

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.appComponent
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.config.Config
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import javax.inject.Inject

typealias CreateConfigWorkflowRunner = MainCommandOutputWorkflowRunner

/**
 * Command to initialize CLI tool.
 *
 * Usage: $ work init
 *
 * Prompts for jira credentials (email/api token) and git directory info
 */
class InitCommand @Inject constructor(private val workflowRunner: CreateConfigWorkflowRunner) : CliktCommand(name = "init") {

    override fun run() {
        workflowRunner.run()
    }
}