package com.wclausen.work.command.start

import com.google.common.truth.Truth.assertThat
import com.squareup.workflow.testing.testFromStart
import com.squareup.workflow.testing.testFromState
import com.wclausen.work.command.base.Command
import com.wclausen.work.command.base.Output
import com.wclausen.work.command.start.StartWorkingWorkflow.Companion.LOADING_TASKS_FAILED_MESSAGE
import com.wclausen.work.config.Config
import com.wclausen.work.config.JiraConfig
import com.wclausen.work.fake.FakeGitService
import com.wclausen.work.fake.FakeJiraService
import com.wclausen.work.workflowext.assertContainsMessage
import com.wclausen.work.workflowext.assertIsMessage
import com.wclausen.work.workflowext.assertIsPrompt
import com.wclausen.work.workflowext.first
import com.wclausen.work.workflowext.multipleCommands
import com.wclausen.work.workflowext.then
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class StartWorkflowTest {

    @Test
    fun `GIVEN no errors WHEN workflow runs THEN completes successfully`() {
        val config = Config(JiraConfig("user@email.com", "fake_jira_token"))
        val fakeJiraService = FakeJiraService()
        StartWorkflow(fakeJiraService, FakeGitService()).testFromStart(config) {
            first().assertIsMessage("Loading jira tasks...")
            then().assertIsMessage(formattedTaskList(fakeJiraService.getTasksForCurrentUserResponse().issues))
            then().assertIsPrompt("Please select a task").nextAction("1")
            then().multipleCommands({
                (it as Command.Echo).assertContainsMessage("Selected task: ")
            }, {
                (it as Command.Prompt).assertIsPrompt("Describe the finished state of the task")
                    .nextAction("done")
            })

            then().assertIsMessage("Saved goal")
            then().assertContainsMessage("Checked out branch")
        }
    }

    @Test
    fun `GIVEN error loading jira tasks WHEN running start command THEN shows error`() {
        val config = Config(JiraConfig("user@email.com", "fake_jira_token"))
        val fakeJiraService = FakeJiraService()
        fakeJiraService.throws = true
        StartWorkflow(fakeJiraService, FakeGitService()).testFromStart(config) {
            first().assertIsMessage("Loading jira tasks...")
            then().assertIsMessage(LOADING_TASKS_FAILED_MESSAGE)
        }
    }

    @Test
    fun `GIVEN invalid task selection WHEN selecting tasks THEN prompts for selection again`() =
        runBlockingTest {
            val config = Config(JiraConfig("user@email.com", "fake_jira_token"))
            val fakeJiraService = FakeJiraService()
            val tasks = fakeJiraService.getTasksForCurrentUser().issues
            StartWorkflow(fakeJiraService, FakeGitService()).testFromState(
                config, StartWorkflow.State.TaskSelectionNeeded.FirstTime(tasks)
            ) {
                first().assertIsMessage(formattedTaskList(tasks))
                then().assertIsPrompt("Please select a task")
                    .nextAction("10000") // make invalid selction, number larger than number of tasks
                then().assertContainsMessage("Your selection was invalid. Please select within")
                then().assertIsPrompt("Please select a task").nextAction("10001")
                then().assertContainsMessage("Your selection was invalid. Please select within")
                then().assertIsPrompt("Please select a task")
            }
        }

    @Test
    fun `GIVEN error WHEN checking out branch THEN show error and return`() = runBlockingTest {
        val config = Config(JiraConfig("user@email.com", "fake_jira_token"))
        val fakeJiraService = FakeJiraService()
        val tasks = fakeJiraService.getTasksForCurrentUser().issues
        val selectedTask = tasks[0]
        val gitService = FakeGitService()
        gitService.throws = true
        StartWorkflow(fakeJiraService, gitService).testFromState(
            config, StartWorkflow.State.TaskSelected(
                selectedTask
            )
        ) {
            first().multipleCommands({
                (it as Command.Echo).assertIsMessage("Selected task: ${selectedTask.key}")
            }, {
                (it as Command.Prompt).assertIsPrompt("Describe the finished state of the task")
                    .nextAction("some goal")
            })
            then().assertIsMessage("Failed to checkout branch for task")
            then().assertFinishes()
        }
    }
}

private fun <T> Output<T>.assertFinishes() {
    assertThat(this).isInstanceOf(Output.Final::class.java)
}
