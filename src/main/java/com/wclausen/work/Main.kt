package com.wclausen.work

import com.github.ajalt.clikt.core.subcommands
import com.wclausen.work.command.base.WorkCommand
import com.wclausen.work.command.comment.CommentCommand
import com.wclausen.work.command.commit.CommitCommand
import com.wclausen.work.command.diff.DiffCommand
import com.wclausen.work.command.done.DoneCommand
import com.wclausen.work.command.init.InitCommand
import com.wclausen.work.command.start.StartCommand
import com.wclausen.work.command.update.UpdateCommand
import com.wclausen.work.inject.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

val appComponent = DaggerAppComponent.create()

@ExperimentalCoroutinesApi
@FlowPreview
fun main(args: Array<String>) =
    appComponent.workCommand.main(args)
