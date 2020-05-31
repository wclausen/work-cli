package com.wclausen.work.command.init

import com.github.michaelbull.result.get
import com.google.common.truth.Truth.assertThat
import com.squareup.workflow.testing.WorkflowTester
import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.Output
import com.wclausen.work.config.Config
import org.junit.Test

class InitWorkflowTest {

    @Test
    fun `GIVEN no errors WHEN running workflow THEN completes entire flow`() {
        // Test of entire flow, mostly to show the kinds of tests possible with workflow :)
        val expected_username = "some_username"
        val expected_token = "some_token"
        InitWorkflow().testFromStart {
            first()
                .asksForUsername()
                .whenUsernameProvided(expected_username)
            then()
                .asksForToken()
                .whenTokenProvided(expected_token)
            then()
                .emitsConfig()
                .withDetails(
                    username = expected_username,
                    token = expected_token)
        }
    }
}

private fun Config.withDetails(username: String, token : String) {
    assertThat(jira.jira_email).isEqualTo(username)
    assertThat(jira.jira_api_token).isEqualTo(token)
}

private fun Output.emitsConfig(): Config {
    assertThat(this).isInstanceOf(Output.Final::class.java)
    return (this as Output.Final<*>).result.get() as Config
}

private fun Command.Prompt.whenTokenProvided(token: String) = nextAction.invoke(token)

private fun Output.asksForToken(): Command.Prompt {
    return assertIsPrompt(InitWorkflow.GET_JIRA_API_TOKEN_PROMPT)
}

private fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.first(): OutputT = awaitNextOutput()
private fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.then(): OutputT = awaitNextOutput()

private fun Command.Prompt.whenUsernameProvided(username: String) = nextAction.invoke(username)

private fun Output.asksForUsername(): Command.Prompt {
    return assertIsPrompt(InitWorkflow.GET_USERNAME_PROMPT)
}

private fun Output.assertIsPrompt(expectedText: String): Command.Prompt {
    val output = this as Output.InProgress
    val prompt = output.command as Command.Prompt
    assertThat(prompt.prompt).isEqualTo(expectedText)
    return prompt
}
