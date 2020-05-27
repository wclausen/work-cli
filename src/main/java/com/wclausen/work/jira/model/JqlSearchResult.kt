package com.wclausen.work.jira.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JqlSearchResult(val issues: List<IssueBean>)

@JsonClass(generateAdapter = true)
data class IssueBean(
    val id: String,
    val self: String,
    val key: String,
    val fields: JqlSearchResultIssueFields)

@JsonClass(generateAdapter = true)
data class JqlSearchResultIssueFields(
    val summary: String,
    val description: String?
)
