package com.wclausen.work.base

import com.squareup.moshi.FromJson

/**
 * Meant to encapsulate the highest level of state in the system.
 *
 * This state determines what commands are valid at startup. For example, if a user tries to run
 * `work start` from an [WorkflowState.Uninitialized] then they should be prompted to first
 * initialize the CLI tool (provide jira creds, specify git repo, etc).
 *
 * TODO: actually use this state to determine valid actions, persist across invocations of CLI
 *
 */
sealed class WorkflowState() {

    object Uninitialized : WorkflowState()

    object Waiting : WorkflowState()

    class Executing(val taskId: String) : WorkflowState()

}