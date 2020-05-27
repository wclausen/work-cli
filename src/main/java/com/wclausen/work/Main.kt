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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
fun main(args: Array<String>) =
    WorkCommand()
        .subcommands(
            InitCommand(),
            StartCommand(),
            UpdateCommand(),
            CommentCommand(),
            CommitCommand(),
            DiffCommand(),
            DoneCommand()
        )
        .main(args)
