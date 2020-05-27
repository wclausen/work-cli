package com.wclausen.work.command.base

import com.github.ajalt.clikt.core.CliktCommand

/**
 * Base command for running any top-level initialization needed by other commands
 */
class WorkCommand : CliktCommand(name = "work") {

    /** This will be executed prior to delegation to other command classes.
     * Could be a good place to initialize object graphs/determine high-level
     * state i.e. [WorkflowState]
     */
    override fun run() {
    }

}