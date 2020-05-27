package com.wclausen.work.jira.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JiraUser(
    val self: String,
    val key: String?,
    val accountId: String,
    val emailAddress: String)
