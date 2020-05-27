package com.wclausen.work.jira.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IssueFields(
    val summary: String,
    val project: JiraProjectId,
    val issuetype: JiraIssueType
)
