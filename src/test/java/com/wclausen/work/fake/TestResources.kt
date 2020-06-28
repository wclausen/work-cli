package com.wclausen.work.fake

import java.nio.file.Paths

object TestResources {
    val fakeConfigPath = Paths.get(javaClass.getResource("/fake_work_config").toURI())
    val fakeLogPath = Paths.get(javaClass.getResource("/test_work_log").toURI())
}