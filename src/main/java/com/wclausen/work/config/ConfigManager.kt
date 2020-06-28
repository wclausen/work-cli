package com.wclausen.work.config

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import com.wclausen.work.inject.ConfigFile
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import javax.inject.Inject

interface ConfigManager {

    val configLocation: Path

    fun createConfig(config: Config): Result<Config, Error>

    fun getConfig(): Result<Config, Error.ConfigLoadingError>

    sealed class Error(message: String, cause: Throwable) : Throwable(message, cause) {

        class FailedToCreateConfig(configLocation: Path, cause: Throwable) :
            Error("Failed to create config file at: $configLocation", cause)

        class FailedToWriteToConfigFile(configLocation: Path, cause: Throwable) :
            Error("Failed to write config data at: $configLocation", cause)

        sealed class ConfigLoadingError(message: String, cause: Throwable) : Error(message, cause) {

            class NoConfigFileError(message: String) :
                ConfigLoadingError(message, FileNotFoundException())

            class ParsingFileError(message: String) :
                ConfigLoadingError(message, IllegalStateException())

        }

    }

}

// TODO: inject com.wclausen.work.config file info rather than pulling from ConfigFileInfo object
class RealConfigManager @Inject constructor(@ConfigFile configPath: Path) : ConfigManager {

    override val configLocation = configPath

    companion object {
        const val UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE = "Failed to open file"
        const val FAILED_TO_READ_CONFIG_MESSAGE =
            "Failed to read lines from com.wclausen.work.config"
        const val FAILED_TO_PARSE_JSON_MESSAGE = "Failed to parse com.wclausen.work.config data"
    }

    override fun createConfig(config: Config): Result<Config, ConfigManager.Error> {
        return createConfigFileInFileSystem().andThen { writeConfigFields(it, config) }
    }

    override fun getConfig(): Result<Config, ConfigManager.Error.ConfigLoadingError> {
        return openFile().andThen(::readLines).andThen(::parseJson)
    }

    private fun openFile() = runCatching {
        configLocation.source().buffer()
    }.mapError { it.toConfigError(UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE) }

    private fun readLines(bufferedSource: BufferedSource) = runCatching {
        bufferedSource.readUtf8()
    }.mapError { it.toConfigError(FAILED_TO_READ_CONFIG_MESSAGE) }

    private fun parseJson(json: String) = runCatching {
        val moshi = Moshi.Builder().build()
        val configJsonAdapter = moshi.adapter(Config::class.java)
        configJsonAdapter.fromJson(json)!!
    }.mapError { it.toConfigError(FAILED_TO_PARSE_JSON_MESSAGE) }

    private fun writeConfigFields(configPath: Path, config: Config) = runCatching {
        val moshi = Moshi.Builder().build()
        val configJsonAdapter = moshi.adapter(Config::class.java).indent("  ")
        val configContents = configJsonAdapter.toJson(config)
        val configWriter = Files.newBufferedWriter(configPath)
        configWriter.write(configContents)
        configWriter.flush()
        config
    }.mapError { ConfigManager.Error.FailedToWriteToConfigFile(configPath, it) }

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
    }.mapError { ConfigManager.Error.FailedToCreateConfig(configLocation, it) }

}

private fun Throwable.toConfigError(helperMessage: String) = if (this is FileNotFoundException) {
    ConfigManager.Error.ConfigLoadingError.NoConfigFileError(helperMessage + "\n" + message)
} else {
    ConfigManager.Error.ConfigLoadingError.ParsingFileError(helperMessage + "\n" + message)
}
