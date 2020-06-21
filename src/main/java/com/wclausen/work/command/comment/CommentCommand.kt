package com.wclausen.work.command.comment

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.inject.CommentCommandRunner
import javax.inject.Inject

/**
 * Adds a comment to the Jira issue currently being worked on
 *
 * Usage: $ work comment -m {message}
 *
 */
class CommentCommand @Inject constructor(@CommentCommandRunner private val commentWorkflowRunner: MainCommandOutputWorkflowRunner) :
    CliktCommand(name = "comment") {

    // TODO: implement support for -m argument

    override fun run() {
        commentWorkflowRunner.run()
    }
}