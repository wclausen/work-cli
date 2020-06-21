package com.wclausen.work.config

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File
import java.io.FileNotFoundException
import javax.inject.Inject

interface ConfigReader {

    fun getConfig(): Result<Config, ConfigLoadingError>
}

sealed class ConfigLoadingError(val message: String) {

    class NoConfigFileError(message: String) : ConfigLoadingError(message)

    class ParsingFileError(message: String) : ConfigLoadingError(message)

}

class RealConfigReader @Inject constructor(private val configFile: File) : ConfigReader {

    companion object {
        const val UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE = "Failed to open file"
        const val FAILED_TO_READ_CONFIG_MESSAGE = "Failed to read lines from com.wclausen.work.config"
        const val FAILED_TO_PARSE_JSON_MESSAGE = "Failed to parse com.wclausen.work.config data"

    }

    override fun getConfig(): Result<Config, ConfigLoadingError> {
        return openFile()
            .andThen(::readLines)
            .andThen(::parseJson)
    }

    private fun openFile() = runCatching {
        configFile.source().buffer()
    }.mapError { it.toConfigError(UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE) }

    private fun readLines(bufferedSource: BufferedSource) = runCatching {
        bufferedSource.readUtf8()
    }.mapError { it.toConfigError(FAILED_TO_READ_CONFIG_MESSAGE) }

    private fun parseJson(json: String) = runCatching {
        val moshi = Moshi.Builder().build()
        val configJsonAdapter = moshi.adapter(Config::class.java)
        configJsonAdapter.fromJson(json)!!
    }.mapError { it.toConfigError(FAILED_TO_PARSE_JSON_MESSAGE) }

}

private fun Throwable.toConfigError(helperMessage: String) =
    if (this is FileNotFoundException) {
        ConfigLoadingError.NoConfigFileError(helperMessage + "\n" + message)
    } else {
        ConfigLoadingError.ParsingFileError(helperMessage + "\n" + message)
    }




