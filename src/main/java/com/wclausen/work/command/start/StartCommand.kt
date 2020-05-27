package com.wclausen.work.command.start


import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.michaelbull.result.Result
import com.wclausen.work.base.CommandWorkflowRunner
import com.wclausen.work.base.WorkflowState
import com.wclausen.work.git.RealGitService
import com.wclausen.work.jira.jiraService
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File

class StartCommand : CliktCommand(name = "start"){

    val taskId by argument().optional()

    override fun run() {
        val workingDir = File("/Users/wclausen/code/git_messaround")
        val repo = RepositoryBuilder()
            .findGitDir(workingDir)
            .build()
        println(
            CommandWorkflowRunner<Unit, Result<WorkflowState, StartWorkingWorkflow.StartTaskError>>(
            ConflatedBroadcastChannel(Unit),
            StartWorkingWorkflow(taskId, jiraService, RealGitService(Git(repo)))
        ).run())
    }

}
