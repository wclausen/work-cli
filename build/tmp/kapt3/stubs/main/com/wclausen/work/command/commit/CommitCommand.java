package com.wclausen.work.command.commit;

import java.lang.System;

/**
 * Commits modified files to Git repo.
 *
 * Usage: $ work commit -m {message}
 *
 * Under the hood just runs `git commit -am {message}`. Won't add untracked filesnn
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0017\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/commit/CommitCommand;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "()V", "run", "", "work-cli"})
public final class CommitCommand extends com.github.ajalt.clikt.core.CliktCommand {
    
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @kotlinx.coroutines.FlowPreview()
    @java.lang.Override()
    public void run() {
    }
    
    public CommitCommand() {
        super(null, null, null, false, false, null, null, false);
    }
}