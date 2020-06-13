package com.wclausen.work.workflowext

import com.google.common.truth.Truth.assertThat
import com.squareup.workflow.testing.WorkflowTester
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.Output


fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.first(): OutputT = awaitNextOutput()
fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.then(): OutputT = awaitNextOutput()

fun <T> Output<T>.assertIsPrompt(expectedText: String): Command.Prompt {
    val output = this as Output.InProgress
    val prompt = output.command as Command.Prompt
    assertThat(prompt.prompt).isEqualTo(expectedText)
    return prompt
}

fun <T> Output<T>.assertIsMessage(expectedText: String) {
    val output = this as Output.InProgress
    val echo = output.command as Command.Echo
    assertThat(echo.output).isEqualTo(expectedText)
}

fun <T> Output<T>.assertContainsMessage(expectedText: String) {
    val output = this as Output.InProgress
    val echo = output.command as Command.Echo
    assertThat(echo.output).contains(expectedText)
}