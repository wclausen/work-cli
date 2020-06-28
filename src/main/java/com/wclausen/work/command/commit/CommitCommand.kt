package com.wclausen.work.command.commit

import com.github.ajalt.clikt.core.CliktCommand
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.inject.CommitCommandRunner
import com.wclausen.work.task.RealTaskManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File
import javax.inject.Inject

/**
 * Commits modified files to Git repo.
 *
 * Usage: $ work commit -m {message}
 *
 * Under the hood just runs `git commit -am {message}`. Won't add untracked filesnn
 */
class CommitCommand @Inject constructor(@CommitCommandRunner private val commitWorkflowRunner: MainCommandOutputWorkflowRunner) :
    CliktCommand(name = "commit") {

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun run() {
        commitWorkflowRunner.run()
        val workingDir =
            File(System.getProperty("user.dir")) // TODO: make this read from com.wclausen.work.config
        val repo = RepositoryBuilder().findGitDir(workingDir).build()
        val currentTask = RealTaskManager().getCurrentTask()!!
//        println(
//            CommandWorkflowRunner(
//            ConflatedBroadcastChannel(Unit),
//            CommitWorkflow(currentTask, RealGitService(Git(repo)))
//        ).run())
    }
}