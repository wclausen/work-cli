package com.wclausen.work.config

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import com.wclausen.work.config.ConfigFileInfo.configDirectoryName
import com.wclausen.work.config.ConfigFileInfo.configFilePath
import java.nio.file.Files
import java.nio.file.Path

interface ConfigCreator {

    val configLocation : Path

    fun createConfigFile(config: Config): Result<Unit, Error>

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause) {

        class FailedToCreateConfig(configLocation: Path, cause: Throwable) : Error("Failed to create config file at: $configLocation", cause)

        class FailedToWriteToConfigFile(configLocation: Path, cause: Throwable) : Error("Failed to write config data at: $configLocation", cause)

    }

}

// TODO: inject com.wclausen.work.config file info rather than pulling from ConfigFileInfo object
class RealConfigCreator : ConfigCreator {

    override val configLocation = configFilePath

    override fun createConfigFile(config: Config): Result<Unit, ConfigCreator.Error> {
        return createConfigFileInFileSystem()
            .andThen { writeConfigFields(it, config) }
    }

    private fun writeConfigFields(configPath: Path, config: Config) = runCatching {
        val moshi = Moshi.Builder()
            .build()
        val configJsonAdapter = moshi.adapter(Config::class.java).indent("  ")
        val configContents = configJsonAdapter.toJson(config)
        val configWriter = Files.newBufferedWriter(configPath)
        configWriter.write(configContents)
        configWriter.flush()
    }.mapError { ConfigCreator.Error.FailedToWriteToConfigFile(configPath, it) }

    private fun createConfigFileInFileSystem() = runCatching {
        if (Files.notExists(configLocation)) {
            val configDirectory = configLocation.parent
            println("Creating workflow.properties file at: $configDirectory")
            Files.createDirectories(configDirectory)
            Files.createFile(configLocation)
        } else {
            println("Config file found at: $configLocation")
            configLocation
        }
    }.mapError { ConfigCreator.Error.FailedToCreateConfig(configLocation, it) }

}
