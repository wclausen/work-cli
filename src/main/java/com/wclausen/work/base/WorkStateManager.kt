package com.wclausen.work.base

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.wclausen.work.config.ConfigManager
import com.wclausen.work.inject.WorkLogFile
import okio.BufferedSource
import okio.buffer
import okio.sink
import okio.source
import java.nio.file.Path
import java.nio.file.StandardOpenOption.APPEND

interface WorkStateManager {

    fun getWorkflowState(): Result<WorkState, Error.ReadStateError>

    fun writeUpdateToLog(update: WorkUpdate): Result<Unit, Error>

    sealed class Error() {

        sealed class ReadStateError() : Error() {

            class ConfigError : ReadStateError()

            class NoLogFileError : ReadStateError()

            class EmptyLogFileError : ReadStateError()

            data class MalformedLogError(val cause: Throwable) : ReadStateError()

        }

        class WorkUpdateWriteFailed : Error()

    }
}

class RealWorkStateManager(
    @WorkLogFile private val logFile: Path, private val configManager: ConfigManager
) : WorkStateManager {

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

    override fun getWorkflowState(): Result<WorkState, WorkStateManager.Error.ReadStateError> {
        return configManager.getConfig()
            .mapError { WorkStateManager.Error.ReadStateError.ConfigError() }.andThen { openLog() }
            .andThen(::readLastLine).andThen(::parseLastWorkUpdate).map { mapToWorkflowState(it) }
    }

    private fun mapToWorkflowState(workUpdate: WorkUpdate): WorkState = when (workUpdate) {
        is WorkUpdate.Start -> WorkState.Executing(
            taskId = workUpdate.taskId,
            goal = workUpdate.goal
        )
        is WorkUpdate.JiraComment -> WorkState.Executing(
            taskId = workUpdate.taskId,
            goal = workUpdate.goal
        )
        is WorkUpdate.GitCommit -> WorkState.Executing(workUpdate.taskId, workUpdate.goal)
        is WorkUpdate.FinishedTask -> WorkState.Waiting
    }

    override fun writeUpdateToLog(update: WorkUpdate): Result<Unit, WorkStateManager.Error.WorkUpdateWriteFailed> {
        val jsonParser = moshi.adapter(WorkUpdate::class.java)
        return runCatching {
            logFile.sink(APPEND).buffer().writeUtf8(jsonParser.toJson(update) + "\n").flush()
            Unit
        }.mapError { WorkStateManager.Error.WorkUpdateWriteFailed() }
    }

    private fun parseLastWorkUpdate(stateJson: String): Result<WorkUpdate, WorkStateManager.Error.ReadStateError> =
        runCatching {
            val configJsonAdapter = moshi.adapter(WorkUpdate::class.java)
            configJsonAdapter.fromJson(stateJson)!!
        }.mapError { WorkStateManager.Error.ReadStateError.MalformedLogError(it) }

    private fun readLastLine(source: BufferedSource): Result<String, WorkStateManager.Error.ReadStateError> =
        runCatching {
            var line: String? = null
            while (!source.exhausted()) {
                line = source.readUtf8Line()
            }
            line ?: throw Exception("Empty log")
        }.mapError { WorkStateManager.Error.ReadStateError.EmptyLogFileError() }

    private fun openLog(): Result<BufferedSource, WorkStateManager.Error.ReadStateError> =
        runCatching {
            logFile.source().buffer()
        }.mapError { WorkStateManager.Error.ReadStateError.NoLogFileError() }


}