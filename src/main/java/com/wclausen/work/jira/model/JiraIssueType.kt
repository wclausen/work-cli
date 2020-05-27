package com.wclausen.work.jira.api.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
data class JiraIssueType(val id: JiraIssueTypeId)

enum class JiraIssueTypeId(val id: String) {
    TASK("10002")
}

class JiraIssueTypeIdAdapter {
    @ToJson
    fun toJson(jiraIssueTypeId: JiraIssueTypeId): String {
        return jiraIssueTypeId.id
    }

    @FromJson
    fun fromJson(json: String): JiraIssueTypeId {
        return JiraIssueTypeId.values()
                .first { it.id == json }
    }
}