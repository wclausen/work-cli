package com.wclausen.work.inject

import com.github.ajalt.clikt.core.subcommands
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.command.base.MainWorkflow
import com.wclausen.work.command.base.WorkCommand
import com.wclausen.work.command.comment.CommentCommand
import com.wclausen.work.command.commit.CommitCommand
import com.wclausen.work.command.commit.CommitWorkflow
import com.wclausen.work.command.diff.DiffCommand
import com.wclausen.work.command.done.DoneCommand
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.command.init.InitCommand
import com.wclausen.work.command.init.NoOpCommandWorkflow
import com.wclausen.work.command.start.StartCommand
import com.wclausen.work.command.start.StartWorkflow
import com.wclausen.work.command.update.UpdateCommand
import com.wclausen.work.commands.comment.CommentWorkflow
import com.wclausen.work.config.ConfigCreator
import com.wclausen.work.config.ConfigFileInfo
import com.wclausen.work.config.ConfigReader
import com.wclausen.work.config.RealConfigCreator
import com.wclausen.work.config.RealConfigReader
import com.wclausen.work.git.GitService
import com.wclausen.work.git.RealGitService
import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.realJiraService
import com.wclausen.work.task.RealTaskManager
import com.wclausen.work.task.TaskManager
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File

@Module
class AppModule {

    @Provides
    fun taskManager(): TaskManager = RealTaskManager()

    @Provides
    fun configFile(): File = ConfigFileInfo.configFile

    @Provides
    fun configReader(configFile: File): ConfigReader = RealConfigReader(configFile)

    @Provides
    fun configCreator(): ConfigCreator = RealConfigCreator()

    @Provides
    fun jiraService(): JiraService = realJiraService

    @Provides
    fun gitClient(): Git {
        val workingDir = File(System.getProperty("user.dir"))
        val repo = RepositoryBuilder().findGitDir(workingDir).build()
        return Git(repo)
    }

    @Provides
    fun gitService(git: Git): GitService = RealGitService(git)

    @Provides
    fun createConfigWorkflow(configCreator: ConfigCreator): CreateConfigWorkflow =
        CreateConfigWorkflow(configCreator)

    @Provides
    fun initCommandWorkflow(
        configReader: ConfigReader, createConfigWorkflow: CreateConfigWorkflow
    ): MainWorkflow<NoOpCommandWorkflow> =
        MainWorkflow(configReader, createConfigWorkflow, NoOpCommandWorkflow())

    @ExperimentalCoroutinesApi
    @Provides
    @InitCommandRunner
    fun initWorkflowRunner(initWorkflow: MainWorkflow<NoOpCommandWorkflow>): MainCommandOutputWorkflowRunner {
        return MainCommandOutputWorkflowRunner(initWorkflow)
    }

    @Provides
    fun startCommandWorkflow(
        configReader: ConfigReader,
        createConfigWorkflow: CreateConfigWorkflow,
        startWorkflow: StartWorkflow
    ): MainWorkflow<StartWorkflow> = MainWorkflow(configReader, createConfigWorkflow, startWorkflow)


    @ExperimentalCoroutinesApi
    @Provides
    @StartCommandRunner
    fun startWorkflowRunner(startWorkflow: MainWorkflow<StartWorkflow>): MainCommandOutputWorkflowRunner {
        return MainCommandOutputWorkflowRunner(startWorkflow)
    }

    @Provides
    fun commentCommandWorkflow(
        configReader: ConfigReader,
        createConfigWorkflow: CreateConfigWorkflow,
        commentWorkflow: CommentWorkflow
    ): MainWorkflow<CommentWorkflow> =
        MainWorkflow(configReader, createConfigWorkflow, commentWorkflow)


    @ExperimentalCoroutinesApi
    @Provides
    @CommentCommandRunner
    fun commentWorkflowRunner(commentWorkflow: MainWorkflow<CommentWorkflow>): MainCommandOutputWorkflowRunner {
        return MainCommandOutputWorkflowRunner(commentWorkflow)
    }

    @Provides
    fun commitCommandWorkflow(
        configReader: ConfigReader,
        createConfigWorkflow: CreateConfigWorkflow,
        commitWorkflow: CommitWorkflow
    ): MainWorkflow<CommitWorkflow> =
        MainWorkflow(configReader, createConfigWorkflow, commitWorkflow)


    @ExperimentalCoroutinesApi
    @Provides
    @CommitCommandRunner
    fun commitWorkflowRunner(commentWorkflow: MainWorkflow<CommentWorkflow>): MainCommandOutputWorkflowRunner {
        return MainCommandOutputWorkflowRunner(commentWorkflow)
    }

    @Provides
    fun workCommand(
        initCommand: InitCommand,
        startCommand: StartCommand,
        commentCommand: CommentCommand,
        commitCommand: CommitCommand
    ): WorkCommand = WorkCommand().subcommands(
        initCommand,
        startCommand,
        commentCommand,
        commitCommand,
        UpdateCommand(),
        DiffCommand(),
        DoneCommand()
    )

}

@Component(modules = [AppModule::class])
interface AppComponent {
    val workCommand: WorkCommand
}