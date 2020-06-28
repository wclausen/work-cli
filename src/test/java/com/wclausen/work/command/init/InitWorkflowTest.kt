package com.wclausen.work.command.init

import com.github.michaelbull.result.Err
import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.config.ConfigManager
import com.wclausen.work.fake.FakeConfigManager
import com.wclausen.work.fake.TestResources
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class InitWorkflowTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun `GIVEN no config file WHEN starting workflow THEN prompts for config info`() {
        testConfigError(ConfigManager.Error.ConfigLoadingError.NoConfigFileError("file not found"))
    }

    @Test
    fun `GIVEN config read error WHEN starting workflow THEN prompts for config info`() {
        testConfigError(ConfigManager.Error.ConfigLoadingError.ParsingFileError("bad json in file"))
    }

    private fun testConfigError(configLoadingError: ConfigManager.Error.ConfigLoadingError) {
        val configManager = FakeConfigManager(TestResources.fakeConfigPath)
        configManager.getConfigResult = Err(configLoadingError)
        InitWorkflow(
            CreateConfigWorkflow(configManager), TestResources.fakeLogPath
        ).testFromStart {
            first().assertIsPrompt(CreateConfigWorkflow.GET_USERNAME_PROMPT)
        }
    }
}