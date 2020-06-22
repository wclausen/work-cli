package com.wclausen.work.base

/**
 * Meant to encapsulate the highest level of state in the system.
 *
 * This state determines what commands are valid at startup. For example, if a user tries to run
 * `work start` from an [WorkState.Uninitialized] then they should be prompted to first
 * initialize the CLI tool (provide jira creds, specify git repo, etc).
 *
 * TODO: actually use this state to determine valid actions, persist across invocations of CLI
 *
 */
sealed class WorkState() {

    object Uninitialized : WorkState()

    object Waiting : WorkState()

    class Executing(val taskId: String) : WorkState()

}

fun WorkState.getTaskId(): String = (this as WorkState.Executing).taskId