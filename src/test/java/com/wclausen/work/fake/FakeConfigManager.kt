package com.wclausen.work.fake

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigManager
import com.wclausen.work.config.JiraConfig
import java.nio.file.Path

class FakeConfigManager(configLocation: Path) : ConfigManager {

    companion object {
        val okConfig = Ok(
            Config(
                JiraConfig(
                    jira_email = "fake@test.com", jira_api_token = "test_api_token"
                )
            )
        )
    }

    override val configLocation: Path = configLocation
    var getConfigResult: Result<Config, ConfigManager.Error.ConfigLoadingError> = okConfig
    var createConfigResult: Result<Config, ConfigManager.Error> = okConfig

    override fun createConfig(config: Config): Result<Config, ConfigManager.Error> {
        return createConfigResult
    }

    override fun getConfig(): Result<Config, ConfigManager.Error.ConfigLoadingError> {
        return getConfigResult
    }

}