package com.wclausen.work.inject

import com.github.michaelbull.result.Result
import com.wclausen.work.base.CommandWorkflowRunner
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigFileInfo
import com.wclausen.work.config.ConfigInfo
import com.wclausen.work.config.ConfigReader
import com.wclausen.work.config.RealConfigReader
import com.wclausen.work.task.RealTaskManager
import com.wclausen.work.task.TaskManager
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import sun.security.provider.ConfigFile
import java.io.File

@Module
class AppModule(private val args: Array<String>) {

    @Provides
    fun mainArgs(): Array<String> = args

    @Provides
    fun taskManager(): TaskManager = RealTaskManager()

    @Provides
    fun configFile(): File = ConfigFileInfo.configFile

    @Provides
    fun configReader(configFile: File): ConfigReader = RealConfigReader(configFile)

}

@Component(modules = [AppModule::class])
interface AppComponent {
    // TODO: expose dependencies needed by workflows
}