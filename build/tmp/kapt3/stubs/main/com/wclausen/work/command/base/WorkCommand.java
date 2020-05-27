package com.wclausen.work.command.base;

import java.lang.System;

/**
 * Base command for running any top-level initialization needed by other commands
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/base/WorkCommand;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "()V", "run", "", "work-cli"})
public final class WorkCommand extends com.github.ajalt.clikt.core.CliktCommand {
    
    /**
     * This will be executed prior to delegation to other command classes.
     * Could be a good place to initialize object graphs/determine high-level
     * state i.e. [WorkflowState]
     */
    @java.lang.Override()
    public void run() {
    }
    
    public WorkCommand() {
        super(null, null, null, false, false, null, null, false);
    }
}