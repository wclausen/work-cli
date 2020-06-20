package com.wclausen.work.command.start

import com.google.common.truth.Truth
import com.wclausen.work.command.base.Command
import com.wclausen.work.fake.FakeGitService
import com.wclausen.work.fake.FakeJiraService
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class StartWorkingWorkflowTest {

    @Test
    fun `GIVEN no task id provided WHEN rendering THEN loads jira tasks`() {
        StartWorkingWorkflow(null, FakeJiraService(), FakeGitService()).testFromStart {
            val loadingTasksRendering = awaitNextRendering(skipIntermediate = false)
            // Shows loading jira tasks first
            val loadingTasksMessage = (loadingTasksRendering as Command.Echo).output
            Truth.assertThat(loadingTasksMessage).isEqualTo(StartWorkingWorkflow.LOADING_TASKS_MESSAGE)

            // Show tasks and ask for selection
            val selectTasksRendering = awaitNextRendering(skipIntermediate = false) as Command.MultipleCommands
            Truth.assertThat(selectTasksRendering.commands.size).isEqualTo(2)
            Truth.assertThat(selectTasksRendering.commands[0]).isInstanceOf(Command.Echo::class.java) // first show tasks
            Truth.assertThat(selectTasksRendering.commands[1]).isInstanceOf(Command.Prompt::class.java) // ask for selection
        }
    }

    @Test
    fun `GIVEN no task id provided AND jira throws error WHEN start THEN reports error and exits`() {
        val jiraService = FakeJiraService()
        val receivedException = Exception("Failed to load from jira")
        jiraService.getTasksForCurrentUserResponse = {
            throw receivedException
        }
        StartWorkingWorkflow(null, jiraService, FakeGitService()).testFromStart {
            val loadingTasksRendering = awaitNextRendering(skipIntermediate = false)
            // Shows loading jira tasks first
            val loadingTasksMessage = (loadingTasksRendering as Command.Echo).output
            Truth.assertThat(loadingTasksMessage).isEqualTo(StartWorkingWorkflow.LOADING_TASKS_MESSAGE)

            // Return error result
            val output = awaitNextOutput()
            Truth.assertThat(output.getError()!!)
                .hasCauseThat()
                .isEqualTo(receivedException)
            Truth.assertThat(output.getError()!!)
                .hasMessageThat()
                .isEqualTo(StartWorkingWorkflow.LOADING_TASKS_FAILED_MESSAGE)
        }
    }

    @Test
    fun `GIVEN invalid task selection WHEN starting task THEN prompt for selection again`() = runBlockingTest {
        val taskSummaries = FakeJiraService().getTasksForCurrentUser().issues
        StartWorkingWorkflow(null, FakeJiraService(), FakeGitService()).testFromState(
            StartWorkingWorkflow.State.TasksLoaded(taskSummaries)
        ) {
            // Show tasks and ask for selection
            val selectTasksRendering =
                awaitNextRendering(skipIntermediate = false) as Command.MultipleCommands
            Truth.assertThat(selectTasksRendering.commands.size).isEqualTo(2)
            Truth.assertThat(selectTasksRendering.commands[0])
                .isInstanceOf(Command.Echo::class.java) // first show tasks
            Truth.assertThat(selectTasksRendering.commands[1])
                .isInstanceOf(Command.Prompt::class.java) // ask for selection

            // Pass invalid selection to prompt command
            val promptCommand = selectTasksRendering.commands[1] as Command.Prompt
            promptCommand.nextAction("asdfasd")

            // Shows error, prompts again
            val repromptRendering = awaitNextRendering() as Command.MultipleCommands
            Truth.assertThat(repromptRendering.commands[0])
                .isInstanceOf(Command.Echo::class.java) // shows error
            Truth.assertThat(repromptRendering.commands[1])
                .isInstanceOf(Command.Prompt::class.java) // prompts again for selection
        }
    }

    @Test
    fun `GIVEN valid task selection WHEN starting task THEN show selection and ask for current goal`() = runBlockingTest {
        val tasks = FakeJiraService().getTasksForCurrentUser().issues
        StartWorkingWorkflow(null, FakeJiraService(), FakeGitService()).testFromState(
            StartWorkingWorkflow.State.TaskSelected(tasks[0])
        ) {
            // Show tasks selection details, prompt for goal for the next work cycle
            val selectTasksRendering =
                awaitNextRendering(skipIntermediate = false) as Command.MultipleCommands
            Truth.assertThat(selectTasksRendering.commands.size).isEqualTo(2)
            Truth.assertThat(selectTasksRendering.commands[0])
                .isInstanceOf(Command.Echo::class.java) // first show tasks
            Truth.assertThat(selectTasksRendering.commands[1])
                .isInstanceOf(Command.Prompt::class.java) // ask for selection

            // Pass invalid selection to prompt command
            val promptCommand = selectTasksRendering.commands[1] as Command.Prompt
            promptCommand.nextAction("asdfasd")

            // Shows error, prompts again
            val repromptRendering = awaitNextRendering() as Command.MultipleCommands
            Truth.assertThat(repromptRendering.commands[0])
                .isInstanceOf(Command.Echo::class.java) // shows task selection
            Truth.assertThat(repromptRendering.commands[1])
                .isInstanceOf(Command.Prompt::class.java) // prompt for goal
        }
    }

//    @Test
//    fun `GIVEN goal provided WHEN starting work THEN checkout branch with task name`() = runBlockingTest {
//        val fakeGitService = FakeGitService()
//        StartWorkingWorkflow(null, FakeJiraService(), fakeGitService).testFromState(StartWorkingWorkflow.State.GoalProvided(
//            IssueBean("10001", "issue_url", "issue_key", JqlSearchResultIssueFields("issue_summary", "issue_description")))) {
//            //
//        }
//    }

}