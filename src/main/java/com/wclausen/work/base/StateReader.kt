package com.wclausen.work.base

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import com.wclausen.work.config.ConfigReader
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File

interface StateReader {

    fun getWorkflowState(): Result<WorkState, Error>

    sealed class Error() {

        class ConfigError : Error()

        class NoLogFileError : Error()

        class EmptyLogFileError : Error()

        class MalformedLogError : Error()

    }
}

class RealStateReader(private val logFile: File, private val configReader: ConfigReader) :
    StateReader {
    override fun getWorkflowState(): Result<WorkState, StateReader.Error> {
        return configReader.getConfig().mapError { StateReader.Error.ConfigError() }
            .andThen { openLog() }.andThen(::readLastLine).andThen(::parseWorkflowState)
    }

    private fun parseWorkflowState(stateJson: String): Result<WorkState, StateReader.Error> =
        runCatching {
            val moshi = Moshi.Builder().build()
            val configJsonAdapter = moshi.adapter(WorkState::class.java)
            configJsonAdapter.fromJson(stateJson)!!
        }.mapError { StateReader.Error.MalformedLogError() }

    private fun readLastLine(source: BufferedSource): Result<String, StateReader.Error> =
        runCatching {
            var line: String? = null
            while (!source.exhausted()) {
                line = source.readUtf8Line()
            }
            line ?: throw Exception("Empty log")
        }.mapError { StateReader.Error.EmptyLogFileError() }

    private fun openLog(): Result<BufferedSource, StateReader.Error> = runCatching {
        logFile.source().buffer()
    }.mapError { StateReader.Error.NoLogFileError() }


}