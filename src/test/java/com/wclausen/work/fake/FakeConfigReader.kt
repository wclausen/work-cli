package com.wclausen.work.fake

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigLoadingError
import com.wclausen.work.config.ConfigReader
import com.wclausen.work.config.JiraConfig

class FakeConfigReader : ConfigReader {
    companion object {
        val okConfig = Ok(
            Config(
                JiraConfig(
                    jira_email = "fake@test.com", jira_api_token = "test_api_token"
                )
            )
        )
    }

    var returnsError = false
    var result: Result<Config, ConfigLoadingError> = okConfig
    override fun getConfig(): Result<Config, ConfigLoadingError> {
        return if (!returnsError) okConfig else Err(ConfigLoadingError.ParsingFileError("Bad config"))

    }
}