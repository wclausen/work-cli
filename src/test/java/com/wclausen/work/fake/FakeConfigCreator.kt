package com.wclausen.work.fake

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.wclausen.work.config.Config
import com.wclausen.work.config.ConfigCreator
import java.nio.file.Path

class FakeConfigCreator(configLocation: Path) : ConfigCreator {

    var creationResult: Result<Unit, ConfigCreator.Error> = Ok(Unit)

    override val configLocation: Path = configLocation

    override fun createConfigFile(config: Config): Result<Unit, ConfigCreator.Error> {
        return creationResult
    }

}