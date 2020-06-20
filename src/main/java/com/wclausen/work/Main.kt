package com.wclausen.work

import com.wclausen.work.inject.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

val appComponent = DaggerAppComponent.create()

@ExperimentalCoroutinesApi
@FlowPreview
fun main(args: Array<String>) =
    appComponent.workCommand.main(args)
