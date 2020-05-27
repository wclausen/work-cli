package com.wclausen.work.command.done

import com.github.ajalt.clikt.core.CliktCommand

/**
 * Command to finish working on a task. By default
 *  - prompts for final comment of jira issue
 *  - adds comment to issue
 *  - updates issue status to "done"
 *  - checks out master and pulls from origin
 *
 * Usage: $ work done
 */
class DoneCommand : CliktCommand(name = "done"){
    override fun run() {
        // TODO: implement done functionality
    }
}