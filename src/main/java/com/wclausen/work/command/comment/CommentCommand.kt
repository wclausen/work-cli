package com.wclausen.work.command.comment

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.CommandWorkflowRunner
import com.wclausen.work.commands.comment.CommentWorkflow
import com.wclausen.work.jira.jiraService
import com.wclausen.work.task.RealTaskManager
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * Adds a comment to the Jira issue currently being worked on
 *
 * Usage: $ work comment -m {message}
 *
 */
class CommentCommand : CliktCommand(name = "comment") {

    // TODO: implement support for -m argument

        override fun run() {
            println(
                CommandWorkflowRunner(
                ConflatedBroadcastChannel(Unit),
                CommentWorkflow(RealTaskManager().getCurrentTask()!!, jiraService)
            ).run())
        }
}