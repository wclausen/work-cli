package com.wclausen.work.config

import java.nio.file.Paths

object ConfigFileInfo {
    val configDirectoryName = System.getProperty("user.home") + "/.workflow/"
    val configFileName = "workflow.properties"

    val configDirectoryPath = Paths.get(configDirectoryName)
    val configFilePath = Paths.get(configDirectoryName + configFileName)

    val configFile = configFilePath.toFile()
}