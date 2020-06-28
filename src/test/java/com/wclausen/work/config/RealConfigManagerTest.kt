package com.wclausen.work.config

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.wclausen.work.config.RealConfigManager.Companion.FAILED_TO_PARSE_JSON_MESSAGE
import com.wclausen.work.config.RealConfigManager.Companion.UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE
import okio.buffer
import okio.sink
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class RealConfigManagerTest {

    @get:Rule
    val tempFolder = TemporaryFolder()

    @Test
    fun `GIVEN missing file WHEN reading config THEN reports unable to open`() {
        val missingFile = tempFolder.newFile("missing_file")
        missingFile.delete()
        val error = RealConfigManager(missingFile.toPath()).getConfig().getError()!!
        assertThat(error.message).contains(UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE)
    }

    @Test
    fun `GIVEN empty file WHEN reading config THEN reports unable to parse json`() {
        val emptyFile = tempFolder.newFile("empty_file")
        val error = RealConfigManager(emptyFile.toPath()).getConfig().getError()!!
        assertThat(error.message).contains(FAILED_TO_PARSE_JSON_MESSAGE)
    }

    @Test
    fun `GIVEN malformed json in file WHEN reading config THEN reports unable to parse json`() {
        val badJsonFile = tempFolder.newFile("bad_json")
        badJsonFile.sink().buffer()
            .writeUtf8("{ \"a_field\": \"some_value\"}") // wrong json contents
            .close()
        val error = RealConfigManager(badJsonFile.toPath()).getConfig().getError()!!
        assertThat(error.message).contains(RealConfigManager.FAILED_TO_PARSE_JSON_MESSAGE)
    }

    @Test
    fun `GIVEN correct json in file WHEN reading config THEN returns result`() {
        val configFile = tempFolder.newFile("workflow.com.wclausen.work.config")
        val expectedConfig = Config(JiraConfig("some_email@fake.com", "asdfjkl;12345"))
        configFile.sink()
            .buffer()
            .writeUtf8(expectedConfig.asJson()) // wrong json contents
            .close()
        val config = RealConfigManager(configFile.toPath()).getConfig().get()!!
        assertThat(config).isEqualTo(expectedConfig)
    }

}

private fun Config.asJson(): String {
    val moshi = Moshi.Builder()
        .build()
    val adapter = moshi.adapter(Config::class.java)
    return adapter.toJson(this)
}
