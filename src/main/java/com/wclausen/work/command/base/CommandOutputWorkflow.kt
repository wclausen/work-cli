package com.wclausen.work.command.base

import com.github.michaelbull.result.Result
import com.squareup.workflow.StatefulWorkflow

/**
 * Command that captures the possible types of output from Workflows.
 *
 * A Workflow will either emit a [Command] or a final result as outputs. When the output is a
 * [Command], the [CommandOutputWorkflowRunner] will execute it. When the output is a final result,
 * it will be handled by the parent workflow and indicates the child has no more work left to do.
 */
sealed class Output {

    data class InProgress(val command: Command) : Output()

    data class Final<T>(val result: Result<T, *>) : Output()
}

/**
 * For simplifying declarations of CommandOutputWorkflows
 *
 * Note, eventually this is intended to replace the CommandWorkflow and once that happens
 * the name of this typealias will change to CommandWorkflow
 */
typealias CommandOutputWorkflow<PropsT, StateT> = StatefulWorkflow<PropsT, StateT, Output, Unit>