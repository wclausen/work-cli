package com.wclausen.work.task

import javax.inject.Inject

interface TaskManager {
    fun hasCurrentTask(): Boolean

    fun getCurrentTask(): String?
}

class RealTaskManager @Inject constructor() : TaskManager {
    override fun hasCurrentTask(): Boolean {
        return false
    }

    override fun getCurrentTask(): String? {
        return "WORK-1"
    }

}
