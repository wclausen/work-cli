package com.wclausen.work.command.comment;

import java.lang.System;

/**
 * Adds a comment to the Jira issue currently being worked on
 *
 * Usage: $ work comment -m {message}
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/comment/CommentCommand;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "()V", "run", "", "work-cli"})
public final class CommentCommand extends com.github.ajalt.clikt.core.CliktCommand {
    
    @java.lang.Override()
    public void run() {
    }
    
    public CommentCommand() {
        super(null, null, null, false, false, null, null, false);
    }
}