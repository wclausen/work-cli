package com.wclausen.work.command.start

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.inject.StartCommandRunner
import javax.inject.Inject

class StartCommand @Inject constructor(@StartCommandRunner private val workflowRunner: MainCommandOutputWorkflowRunner) :
    CliktCommand(name = "start") {

    override fun run() {
        workflowRunner.run()
    }

}