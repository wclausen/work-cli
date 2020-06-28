package com.wclausen.work.config

import java.nio.file.Paths

object ConfigFileInfo {
    val configDirectoryName = System.getProperty("user.home") + "/.workflow/"
    val configFileName = "workflow.properties"

    val configDirectoryPath = Paths.get(configDirectoryName)
    val configFilePath = Paths.get(configDirectoryName + configFileName)

    val configFile = configFilePath.toFile()
}

object WorkLogFileInfo {
    val workLogDirectoryName = System.getProperty("user.home") + "/.workflow/"
    val workLogFileName = "work_log"

    val workLogDirectoryPath = Paths.get(workLogDirectoryName)
    val workLogFilePath = Paths.get(workLogDirectoryName + workLogFileName)

    val workLogFile = workLogFilePath.toFile()
}