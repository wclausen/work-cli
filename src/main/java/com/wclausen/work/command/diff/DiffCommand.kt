package com.wclausen.work.command.diff

import com.github.ajalt.clikt.core.CliktCommand

/**
 * Command to finish working on a task. By default
 *  - runs arc diff
 *  - adds comment to jira issue with link to created diff
 *  - changes status of jira issue to "in review"
 *
 * Usage: $ work diff
 */
class DiffCommand : CliktCommand(name = "diff"){
    override fun run() {
        // TODO: implement diff functionality
    }
}