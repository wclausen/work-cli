package com.wclausen.work.fake

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.wclausen.work.base.WorkState
import com.wclausen.work.base.WorkStateManager
import com.wclausen.work.base.WorkUpdate

class FakeWorkStateManager : WorkStateManager {

    var currentWorkState = Ok(WorkState.Executing("TEST-1"))
    var logUpdateResult = Ok(Unit)
    var lastLogUpdate: WorkUpdate? = null

    override fun getWorkflowState(): Result<WorkState, WorkStateManager.Error.ReadStateError> {
        return currentWorkState
    }

    override fun writeUpdateToLog(update: WorkUpdate): Result<Unit, WorkStateManager.Error> {
        lastLogUpdate = update
        return logUpdateResult
    }
}