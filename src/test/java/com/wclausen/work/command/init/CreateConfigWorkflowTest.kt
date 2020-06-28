package com.wclausen.work.command.init

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.expectError
import com.github.michaelbull.result.get
import com.google.common.truth.Truth.assertThat
import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.Output
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigManager
import com.wclausen.work.config.RealConfigManager
import com.wclausen.work.fake.FakeConfigManager
import com.wclausen.work.fake.TestResources
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import com.wclausen.work.workflowext.then
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.IOException

class CreateConfigWorkflowTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun `GIVEN no errors WHEN running workflow THEN completes entire flow`() {
        // Test of entire flow, mostly to show the kinds of tests possible with workflow :)
        val expected_username = "some_username"
        val expected_token = "some_token"
        val tempConfigPath = TestResources.fakeConfigPath
        val createConfigWorkflow = CreateConfigWorkflow(RealConfigManager(tempConfigPath))
        createConfigWorkflow.testFromStart {
            first().asksForUsername().whenUsernameProvided(expected_username)
            then().asksForToken().whenTokenProvided(expected_token)
            then().emitsSavingFileMessage()
            then()
                .emitsConfig()
                .withDetails(
                    username = expected_username,
                    token = expected_token)
        }
    }

    @Test
    fun `GIVEN io error from config creation WHEN running workflow THEN reports error`() {
        // Test of entire flow, mostly to show the kinds of tests possible with workflow :)
        val expected_username = "some_username"
        val expected_token = "some_token"
        val tempConfigPath = temporaryFolder.newFile().toPath()
        val throwingConfigCreator = FakeConfigManager(tempConfigPath)
        throwingConfigCreator.getConfigResult =
            Err(ConfigManager.Error.ConfigLoadingError.NoConfigFileError("No config present"))
        throwingConfigCreator.createConfigResult =
            Err(ConfigManager.Error.FailedToCreateConfig(tempConfigPath, IOException()))
        val createConfigWorkflow = CreateConfigWorkflow(throwingConfigCreator)
        createConfigWorkflow.testFromStart {
            first().asksForUsername().whenUsernameProvided(expected_username)
            then().asksForToken().whenTokenProvided(expected_token)
            then().emitsSavingFileMessage()
            then()
                .emitsError()
                .withMessage("Failed to create config file")
        }
    }

    @Test
    fun `GIVEN io error writing config data WHEN running workflow THEN reports error`() {
        // Test of entire flow, mostly to show the kinds of tests possible with workflow :)
        val expected_username = "some_username"
        val expected_token = "some_token"
        val tempConfigPath = temporaryFolder.newFile().toPath()
        val throwingConfigCreator = FakeConfigManager(tempConfigPath)
        throwingConfigCreator.getConfigResult =
            Err(ConfigManager.Error.ConfigLoadingError.NoConfigFileError("No config present"))
        throwingConfigCreator.createConfigResult =
            Err(ConfigManager.Error.FailedToWriteToConfigFile(tempConfigPath, IOException()))
        val createConfigWorkflow = CreateConfigWorkflow(throwingConfigCreator)
        createConfigWorkflow.testFromStart {
            first().asksForUsername().whenUsernameProvided(expected_username)
            then().asksForToken().whenTokenProvided(expected_token)
            then().emitsSavingFileMessage()
            then()
                .emitsError()
                .withMessage("Failed to write config data")
        }
    }
}

private fun Throwable.withMessage(expected: String) {
    assertThat(message).contains(expected)
}

private fun <T> Output<T>.emitsError(): Throwable {
    assertThat(this).isInstanceOf(Output.Final::class.java)
    return (this as Output.Final).result.expectError { "expected error but not present" }
}

private fun <T> Output<T>.emitsSavingFileMessage() {
    assertThat(this).isInstanceOf(Output.InProgress::class.java)
    val command = (this as Output.InProgress).command
    assertThat(command).isInstanceOf(Command.Echo::class.java)
    val message = (command as Command.Echo).output
    assertThat(message).startsWith(CreateConfigWorkflow.SAVING_CONFIG_MESSAGE)
}

private fun Config.withDetails(username: String, token : String) {
    assertThat(jira.jira_email).isEqualTo(username)
    assertThat(jira.jira_api_token).isEqualTo(token)
}

private fun <T> Output<T>.emitsConfig(): Config {
    assertThat(this).isInstanceOf(Output.Final::class.java)
    return (this as Output.Final<T>).result.get() as Config
}

private fun Command.Prompt.whenTokenProvided(token: String) = nextAction.invoke(token)

private fun <T> Output<T>.asksForToken(): Command.Prompt {
    return assertIsPrompt(CreateConfigWorkflow.GET_JIRA_API_TOKEN_PROMPT)
}

private fun Command.Prompt.whenUsernameProvided(username: String) = nextAction.invoke(username)

private fun <T> Output<T>.asksForUsername(): Command.Prompt {
    return assertIsPrompt(CreateConfigWorkflow.GET_USERNAME_PROMPT)
}
