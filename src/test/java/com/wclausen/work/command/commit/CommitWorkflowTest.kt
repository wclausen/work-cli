package com.wclausen.work.command.commit

import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.base.WorkState
import com.wclausen.work.fake.FakeGitService
import com.wclausen.work.workflowext.assertFinishes
import com.wclausen.work.workflowext.assertIsMessage
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import com.wclausen.work.workflowext.then
import com.wclausen.work.workflowext.thenUserInputs
import org.junit.Test

class CommitWorkflowTest {

    @Test
    fun `GIVEN waiting for task WHEN executing command THEN returns error`() {
        CommitWorkflow(FakeGitService()).testFromStart(WorkState.Waiting) {
            first().assertIsMessage(CommitWorkflow.NO_TASK_ERROR_MESSAGE)

            then().assertFinishes()
        }
    }

    @Test
    fun `GIVEN cli not initialized WHEN executing command THEN returns error`() {
        CommitWorkflow(FakeGitService()).testFromStart(WorkState.Uninitialized) {
            first().assertIsMessage(CommitWorkflow.NO_TASK_ERROR_MESSAGE)

            then().assertFinishes()
        }
    }

    @Test
    fun `GIVEN no errors WHEN executing command THEN completes successfully`() {
        CommitWorkflow(FakeGitService()).testFromStart(WorkState.Executing("TEST-1")) {
            first().assertIsPrompt(CommitWorkflow.getPromptMessage("TEST-1"))
                .thenUserInputs("some comment")

            then().assertIsMessage(CommitWorkflow.COMMIT_IN_PROGRESS_MESSAGE)

            then().assertIsMessage(CommitWorkflow.SUCCESS_MESSAGE)
        }
    }

    @Test
    fun `GIVEN git fails WHEN committing THEN reports failure to user`() {
        val gitService = FakeGitService()
        gitService.throws = true
        val taskId = "TEST-1"
        CommitWorkflow(gitService).testFromStart(WorkState.Executing(taskId)) {
            first().assertIsPrompt(CommitWorkflow.getPromptMessage(taskId))
                .thenUserInputs("some comment")

            then().assertIsMessage(CommitWorkflow.COMMIT_IN_PROGRESS_MESSAGE)

            then().assertIsMessage(CommitWorkflow.FAILED_TO_COMMIT_MESSAGE)

            then().assertFinishes()
        }
    }

}