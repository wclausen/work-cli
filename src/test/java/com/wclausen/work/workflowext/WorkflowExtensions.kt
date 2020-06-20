package com.wclausen.work.workflowext

import com.google.common.truth.Truth.assertThat
import com.squareup.workflow.testing.WorkflowTester
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.Output


fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.first(): OutputT =
    awaitNextOutput()

fun <PropsT, OutputT : Any, RenderingT> WorkflowTester<PropsT, OutputT, RenderingT>.then(): OutputT =
    awaitNextOutput()

fun <T> Output<T>.assertIsPrompt(expectedText: String): Command.Prompt {
    val output = this as Output.InProgress
    val prompt = output.command as Command.Prompt
    return prompt.assertIsPrompt(expectedText)
}

fun Command.Prompt.assertIsPrompt(expectedText: String): Command.Prompt {
    assertThat(prompt).isEqualTo(expectedText)
    return this
}

fun <T> Output<T>.assertIsMessage(expectedText: String) {
    val output = this as Output.InProgress
    val echo = output.command as Command.Echo
    echo.assertIsMessage(expectedText)
}

fun Command.Echo.assertIsMessage(expectedText: String) {
    assertThat(output).isEqualTo(expectedText)
}

fun <T> Output<T>.assertContainsMessage(expectedText: String) {
    val output = this as Output.InProgress
    val echo = output.command as Command.Echo
    echo.assertContainsMessage(expectedText)
}

fun Command.Echo.assertContainsMessage(expectedText: String) {
    assertThat(output).contains(expectedText)
}

fun <T> Output<T>.multipleCommands(vararg asserts: (Command) -> Unit) {
    val output = this as Output.InProgress
    val multipleCommands = output.command as Command.MultipleCommands
    for (i in asserts.indices) {
        asserts[i].invoke(multipleCommands.commands[i])
    }
}