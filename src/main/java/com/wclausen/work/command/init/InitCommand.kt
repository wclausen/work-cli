package com.wclausen.work.command.init

import com.github.ajalt.clikt.core.CliktCommand

/**
 * Command to initialize CLI tool.
 *
 * Usage: $ work init
 *
 * Prompts for jira credentials (email/api token) and git directory info
 */
class InitCommand : CliktCommand(name = "init"){
    override fun run() {
        // TODO: implement init functionality
    }
}