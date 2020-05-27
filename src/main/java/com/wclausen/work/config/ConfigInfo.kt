package com.wclausen.work.config

import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass

/**
 * Class that enables creation of multiple types of configs
 *
 * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
 * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
 * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
 * serializing/deserializing configs of multiple types.
 */
data class ConfigInfo<T: Any>(val configFile: File, val configClazz: KClass<T>)
