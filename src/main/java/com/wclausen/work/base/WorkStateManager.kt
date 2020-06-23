package com.wclausen.work.base

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.wclausen.work.config.ConfigReader
import okio.BufferedSource
import okio.buffer
import okio.sink
import okio.source
import java.io.File

interface WorkStateManager {

    fun getWorkflowState(): Result<WorkState, Error>

    fun writeUpdateToLog(update: WorkUpdate): Result<Unit, Error>

    sealed class Error() {

        class ConfigError : Error()

        class NoLogFileError : Error()

        class EmptyLogFileError : Error()

        data class MalformedLogError(val cause: Throwable) : Error()

        class WorkUpdateWriteFailed : Error()

    }
}

class RealWorkStateManager(private val logFile: File, private val configReader: ConfigReader) :
    WorkStateManager {

    companion object {
        val moshi = Moshi.Builder().add(
            PolymorphicJsonAdapterFactory.of(WorkUpdate::class.java, "update")
                .withSubtype(WorkUpdate.Start::class.java, UpdateType.START_NEW_TASK.message)
                .withSubtype(
                    WorkUpdate.JiraComment::class.java, UpdateType.JIRA_COMMENT.message
                ).withSubtype(WorkUpdate.GitCommit::class.java, UpdateType.GIT_COMMIT.message)
                .withSubtype(
                    WorkUpdate.FinishedTask::class.java, UpdateType.FINISHED_TASK.message
                )
        ).build()
    }

    override fun getWorkflowState(): Result<WorkState, WorkStateManager.Error> {
        return configReader.getConfig().mapError { WorkStateManager.Error.ConfigError() }
            .andThen { openLog() }.andThen(::readLastLine).andThen(::parseLastWorkUpdate)
            .map { mapToWorkflowState(it) }
    }

    private fun mapToWorkflowState(workUpdate: WorkUpdate): WorkState = when (workUpdate) {
        is WorkUpdate.Start -> WorkState.Executing(taskId = workUpdate.taskId)
        is WorkUpdate.JiraComment -> WorkState.Executing(taskId = workUpdate.taskId)
        is WorkUpdate.GitCommit -> WorkState.Executing(workUpdate.taskId)
        is WorkUpdate.FinishedTask -> WorkState.Waiting
    }

    override fun writeUpdateToLog(update: WorkUpdate): Result<Unit, WorkStateManager.Error> {
        val jsonParser = moshi.adapter(WorkUpdate::class.java)
        return runCatching {
            logFile.sink(append = true).buffer().writeUtf8(jsonParser.toJson(update) + "\n").flush()
            Unit
        }.mapError { WorkStateManager.Error.WorkUpdateWriteFailed() }
    }

    private fun parseLastWorkUpdate(stateJson: String): Result<WorkUpdate, WorkStateManager.Error> =
        runCatching {
            val configJsonAdapter = moshi.adapter(WorkUpdate::class.java)
            configJsonAdapter.fromJson(stateJson)!!
        }.mapError { WorkStateManager.Error.MalformedLogError(it) }

    private fun readLastLine(source: BufferedSource): Result<String, WorkStateManager.Error> =
        runCatching {
            var line: String? = null
            while (!source.exhausted()) {
                line = source.readUtf8Line()
            }
            line ?: throw Exception("Empty log")
        }.mapError { WorkStateManager.Error.EmptyLogFileError() }

    private fun openLog(): Result<BufferedSource, WorkStateManager.Error> = runCatching {
        logFile.source().buffer()
    }.mapError { WorkStateManager.Error.NoLogFileError() }


}