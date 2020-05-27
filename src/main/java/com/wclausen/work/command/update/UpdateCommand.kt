package com.wclausen.work.command.update

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.CommandWorkflowRunner
import com.wclausen.work.git.RealGitService
import com.wclausen.work.jira.jiraService
import com.wclausen.work.task.RealTaskManager
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File

class UpdateCommand : CliktCommand(name = "update") {
    override fun run() {
        val workingDir = File("/Users/wclausen/code/git_messaround")
        val repo = RepositoryBuilder()
            .findGitDir(workingDir)
            .build()
        val currentTask = RealTaskManager().getCurrentTask()!!
        println(
            CommandWorkflowRunner(
            ConflatedBroadcastChannel(Unit),
            UpdateWorkflow(
                currentTask,
                jiraService,
                RealGitService(Git(repo))
            )
        ).run())
    }

}