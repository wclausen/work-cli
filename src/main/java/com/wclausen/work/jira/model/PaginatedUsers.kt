package com.wclausen.work.jira.api.model

import com.squareup.moshi.JsonClass
import com.wclausen.work.jira.api.model.JiraUser

@JsonClass(generateAdapter = true)
data class PaginatedUsers(
    val values: List<JiraUser>)
