package com.wclausen.work.base

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.wclausen.work.fake.FakeConfigReader
import org.junit.Test
import java.io.File

class RealWorkStateManagerTest {

    val workStateManager = RealWorkStateManager(
        File(javaClass.getResource("/test_work_log").file), FakeConfigReader()
    )

    @Test
    fun `GIVEN last update was start task WHEN reading state THEN state is executing`() {
        workStateManager.writeUpdateToLog(
            WorkUpdate.Start(
                "TEST-1", "This is a test", "Done when done"
            )
        )
        assertThat(
            workStateManager.getWorkflowState().get()
        ).isInstanceOf(WorkState.Executing::class.java)
    }

    @Test
    fun `GIVEN last update was jira comment WHEN reading state THEN state is executing`() {
        workStateManager.writeUpdateToLog(
            WorkUpdate.JiraComment(
                "TEST-1", "Made some progress", "Finish the job"
            )
        )
        assertThat(
            workStateManager.getWorkflowState().get()
        ).isInstanceOf(WorkState.Executing::class.java)
    }

    @Test
    fun `GIVEN last update was finished task WHEN reading state THEN state is waiting`() {
        workStateManager.writeUpdateToLog(
            WorkUpdate.FinishedTask(
                "TEST-1"
            )
        )
        assertThat(
            workStateManager.getWorkflowState().get()
        ).isInstanceOf(WorkState.Waiting::class.java)
    }

    @Test
    fun `GIVEN bad config WHEN reading state THEN state is uninitialized`() {
        val configReader = FakeConfigReader()
        configReader.returnsError = true
        val workStateManager = RealWorkStateManager(
            File(javaClass.getResource("/test_work_log").file), configReader
        )
        workStateManager.writeUpdateToLog(
            WorkUpdate.FinishedTask(
                "TEST-1"
            )
        )
        assertThat(
            workStateManager.getWorkflowState().getError()
        ).isInstanceOf(WorkStateManager.Error.ConfigError::class.java)
    }

    @Test
    fun `GIVEN multiple updates in log WHEN reading state THEN returns only most recent update`() {
        workStateManager.writeUpdateToLog(
            WorkUpdate.Start(
                "TEST-1", "This is a test", "Done when done"
            )
        )
        workStateManager.writeUpdateToLog(
            WorkUpdate.JiraComment(
                "TEST-1", "Made some progress", "Finish the job"
            )
        )
        workStateManager.writeUpdateToLog(
            WorkUpdate.FinishedTask(
                "TEST-1"
            )
        )
        val result = workStateManager.getWorkflowState()
        assertThat(
            result.get()
        ).isInstanceOf(WorkState.Waiting::class.java)
    }

    @Test
    fun `each update type logs correctly`() {
        UpdateType.values().map { it.toTestWorkUpdate() }.forEach {
            workStateManager.writeUpdateToLog(it)
            assertThat(workStateManager.getWorkflowState().get()).isInstanceOf(
                mapToWorkflowState(it)::class.java
            )
        }
    }

    private fun mapToWorkflowState(workUpdate: WorkUpdate): WorkState = when (workUpdate) {
        is WorkUpdate.Start -> WorkState.Executing(taskId = workUpdate.taskId)
        is WorkUpdate.JiraComment -> WorkState.Executing(taskId = workUpdate.taskId)
        is WorkUpdate.GitCommit -> WorkState.Executing(workUpdate.taskId)
        is WorkUpdate.FinishedTask -> WorkState.Waiting
    }

}

private fun UpdateType.toTestWorkUpdate(): WorkUpdate {
    return when (this) {
        UpdateType.START_NEW_TASK -> WorkUpdate.Start("TEST-1", "some description", "some goal")
        UpdateType.JIRA_COMMENT -> WorkUpdate.JiraComment(
            "TEST-1", "(test) did some work", "some goal"
        )
        UpdateType.FINISHED_TASK -> WorkUpdate.FinishedTask("TEST-1")
        UpdateType.GIT_COMMIT -> WorkUpdate.GitCommit(
            "TEST-1", "(test) updated some files", "some goal"
        )
    }
}
