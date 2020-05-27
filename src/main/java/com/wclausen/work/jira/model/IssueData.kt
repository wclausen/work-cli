package com.wclausen.work.jira.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IssueData(val fields: IssueFields)
