package com.wclausen.work.inject

import com.github.ajalt.clikt.core.subcommands
import com.wclausen.work.base.MainCommandOutputWorkflowRunner
import com.wclausen.work.command.base.MainWorkflow
import com.wclausen.work.command.base.WorkCommand
import com.wclausen.work.command.comment.CommentCommand
import com.wclausen.work.command.commit.CommitCommand
import com.wclausen.work.command.diff.DiffCommand
import com.wclausen.work.command.done.DoneCommand
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.command.init.InitCommand
import com.wclausen.work.command.init.NoOpCommandWorkflow
import com.wclausen.work.command.start.StartCommand
import com.wclausen.work.command.update.UpdateCommand
import com.wclausen.work.config.ConfigCreator
import com.wclausen.work.config.ConfigFileInfo
import com.wclausen.work.config.ConfigReader
import com.wclausen.work.config.RealConfigCreator
import com.wclausen.work.config.RealConfigReader
import com.wclausen.work.task.RealTaskManager
import com.wclausen.work.task.TaskManager
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
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
    fun createConfigWorkflow(configCreator: ConfigCreator): CreateConfigWorkflow =
        CreateConfigWorkflow(configCreator)

    @Provides
    fun initCommandWorkflow(configReader: ConfigReader, createConfigWorkflow: CreateConfigWorkflow): MainWorkflow<NoOpCommandWorkflow> =
        MainWorkflow(configReader, createConfigWorkflow, NoOpCommandWorkflow())

    @ExperimentalCoroutinesApi
    @Provides
    fun initWorkflowRunner(initWorkflow: MainWorkflow<NoOpCommandWorkflow>): MainCommandOutputWorkflowRunner {
        return MainCommandOutputWorkflowRunner(ConflatedBroadcastChannel(Unit), initWorkflow)
    }

    @Provides
    fun workCommand(initCommand: InitCommand): WorkCommand =
        WorkCommand()
            .subcommands(
                initCommand,
                StartCommand(),
                UpdateCommand(),
                CommentCommand(),
                CommitCommand(),
                DiffCommand(),
                DoneCommand()
            )

}

@Component(modules = [AppModule::class])
interface AppComponent {
    val workCommand: WorkCommand
}