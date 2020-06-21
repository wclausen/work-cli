package com.wclausen.work.command.comment

import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.base.WorkState
import com.wclausen.work.commands.comment.CommentWorkflow
import com.wclausen.work.fake.FakeJiraService
import com.wclausen.work.workflowext.assertFinishes
import com.wclausen.work.workflowext.assertIsMessage
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import com.wclausen.work.workflowext.then
import com.wclausen.work.workflowext.thenUserInputs
import org.junit.Test


class CommentWorkflowTest {

    @Test
    fun `GIVEN waiting for task WHEN executing command THEN returns error`() {
        CommentWorkflow(FakeJiraService()).testFromStart(WorkState.Waiting) {
            first().assertIsMessage(CommentWorkflow.NO_TASK_ERROR_MESSAGE)

            then().assertFinishes()
        }
    }

    @Test
    fun `GIVEN cli not initialized WHEN executing command THEN returns error`() {
        CommentWorkflow(FakeJiraService()).testFromStart(WorkState.Uninitialized) {
            first().assertIsMessage(CommentWorkflow.NO_TASK_ERROR_MESSAGE)

            then().assertFinishes()
        }
    }

    @Test
    fun `GIVEN no errors WHEN executing command THEN completes successfully`() {
        CommentWorkflow(FakeJiraService()).testFromStart(WorkState.Executing("TEST-1")) {
            first().assertIsPrompt(CommentWorkflow.COMMENT_PROMPT).thenUserInputs("some comment")

            then().assertIsMessage(CommentWorkflow.SENDING_COMMENT_TO_JIRA_MESSAGE)

            then().assertIsMessage(CommentWorkflow.SUCCESS_MESSAGE)
        }
    }

    @Test
    fun `GIVEN jira fails WHEN sending comment THEN reports failure to user`() {
        val jiraService = FakeJiraService()
        jiraService.throws = true
        CommentWorkflow(jiraService).testFromStart(WorkState.Executing("TEST-1")) {
            first().assertIsPrompt(CommentWorkflow.COMMENT_PROMPT).thenUserInputs("some comment")

            then().assertIsMessage(CommentWorkflow.SENDING_COMMENT_TO_JIRA_MESSAGE)

            then().assertIsMessage(CommentWorkflow.FAILED_TO_UPDATE_JIRA)

            then().assertFinishes()
        }
    }

}