package com.wclausen.work.config

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Config(val jira: JiraConfig)

@JsonClass(generateAdapter = true)
data class JiraConfig(val jira_email: String, val jira_api_token: String)