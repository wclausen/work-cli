package com.wclausen.work.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class WorkUpdate(@Json(name = "type") val updateType: UpdateType) {
    @JsonClass(generateAdapter = true)
    data class Start(val taskId: String, val taskDescription: String, val goal: String) :
        WorkUpdate(UpdateType.START_NEW_TASK)

    @JsonClass(generateAdapter = true)
    data class JiraComment(val taskId: String, val comment: String, val goal: String) :
        WorkUpdate(UpdateType.JIRA_COMMENT)

    @JsonClass(generateAdapter = true)
    data class GitCommit(val taskId: String, val message: String, val goal: String) :
        WorkUpdate(UpdateType.GIT_COMMIT)

    @JsonClass(generateAdapter = true)
    data class FinishedTask(val taskId: String) : WorkUpdate(UpdateType.FINISHED_TASK)

}

enum class UpdateType(val message: String) {
    START_NEW_TASK("Started a new task"), JIRA_COMMENT("Added jira update"), GIT_COMMIT("Commited to git"), FINISHED_TASK(
        "Finished task"
    )
}