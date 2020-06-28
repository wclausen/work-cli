package com.wclausen.work.command.base

import com.squareup.workflow.testing.testFromStart
import com.wclausen.work.command.init.CreateConfigWorkflow
import com.wclausen.work.command.init.InitWorkflow
import com.wclausen.work.fake.FakeCommandWorkflow
import com.wclausen.work.fake.FakeConfigManager
import com.wclausen.work.fake.FakeWorkStateManager
import com.wclausen.work.fake.TestResources
import com.wclausen.work.workflowext.assertContainsMessage
import com.wclausen.work.workflowext.first
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class MainWorkflowTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    @Test
    fun `GIVEN initial work state of executing WHEN starting workflow THEN runs command`() {
        val fakeWorkStateManager = FakeWorkStateManager()
        MainWorkflow(
            fakeWorkStateManager,
            InitWorkflow(
                CreateConfigWorkflow(
                    FakeConfigManager(
                        temporaryFolder.newFile().toPath()
                    )
                ), TestResources.fakeLogPath
            ),
            FakeCommandWorkflow()
        ).testFromStart {
            first().assertContainsMessage("Running command...")
        }
    }

}