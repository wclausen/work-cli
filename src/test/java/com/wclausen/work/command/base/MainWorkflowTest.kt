package com.wclausen.work.command.base

import com.github.michaelbull.result.Err
import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.config.ConfigLoadingError
import com.wclausen.work.fake.FakeCommandWorkflow
import com.wclausen.work.fake.FakeConfigCreator
import com.wclausen.work.fake.FakeConfigReader
import com.wclausen.work.workflowext.assertContainsMessage
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class MainWorkflowTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun `GIVEN no config file WHEN starting workflow THEN prompts for config info`() {
        testConfigError(ConfigLoadingError.NoConfigFileError("file not found"))
    }

    @Test
    fun `GIVEN config read error WHEN starting workflow THEN prompts for config info`() {
        testConfigError(ConfigLoadingError.ParsingFileError("bad json in file"))
    }

    @Test
    fun `GIVEN valid config present WHEN starting workflow THEN runs command`() {
        val configReader = FakeConfigReader()
        configReader.result = FakeConfigReader.okConfig
        MainWorkflow(
            configReader,
            CreateConfigWorkflow(FakeConfigCreator(temporaryFolder.newFile().toPath())),
            FakeCommandWorkflow()
        ).testFromStart {
            first()
                .assertContainsMessage("Running command...")
        }
    }

    private fun testConfigError(configLoadingError: ConfigLoadingError) {
        val configReader = FakeConfigReader()
        configReader.result = Err(configLoadingError)
        MainWorkflow(
            configReader,
            CreateConfigWorkflow(FakeConfigCreator(temporaryFolder.newFile().toPath())),
            FakeCommandWorkflow()
        ).testFromStart {
            first()
                .assertIsPrompt(CreateConfigWorkflow.GET_USERNAME_PROMPT)
        }
    }
}