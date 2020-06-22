package com.wclausen.work.inject

import javax.inject.Qualifier

@Qualifier
annotation class StartCommandRunner

@Qualifier
annotation class InitCommandRunner

@Qualifier
annotation class CommentCommandRunner

@Qualifier
annotation class CommitCommandRunner