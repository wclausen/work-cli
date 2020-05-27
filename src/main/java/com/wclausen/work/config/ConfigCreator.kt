package com.wclausen.work.config

import com.squareup.moshi.Moshi
import com.wclausen.work.config.ConfigFileInfo.configDirectoryName
import com.wclausen.work.config.ConfigFileInfo.configDirectoryPath
import com.wclausen.work.config.ConfigFileInfo.configFileName
import com.wclausen.work.config.ConfigFileInfo.configFilePath
import java.nio.file.Files

interface ConfigCreator {

    fun createConfig(config: Config)
}

// TODO: inject com.wclausen.work.config file info rather than pulling from ConfigFileInfo object
class RealConfigCreator : ConfigCreator {

    override fun createConfig(config: Config) {
        createConfigFileInFileSystem()
        writeConfigFields(config)
    }

    private fun writeConfigFields(config: Config) {
        val moshi = Moshi.Builder()
            .build()
        val configJsonAdapter = moshi.adapter(Config::class.java).indent("  ")
        val configContents = configJsonAdapter.toJson(config)
        val configWriter = Files.newBufferedWriter(configFilePath)
        configWriter.write(configContents)
        configWriter.flush()
    }

    private fun createConfigFileInFileSystem() {
        if (Files.notExists(configFilePath)) {
            println("Creating com.wclausen.work.config at: $configDirectoryName")
            Files.createDirectories(configDirectoryPath)
            Files.createFile(configFilePath)
        } else {
            println("Config file found at: $configFileName")
        }
    }

}
