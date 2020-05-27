package com.wclausen.work.command.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0004\b\u0001\u0010\u0002*\u0010\b\u0002\u0010\u0003*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00070\u0006B\u0005\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/wclausen/work/command/base/StatefulCommandWorkflow;", "PropsT", "StateT", "OutputT", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/command/base/CommandWorkflow;", "Lcom/squareup/workflow/StatefulWorkflow;", "Lcom/wclausen/work/command/base/Command;", "()V", "work-cli"})
public abstract class StatefulCommandWorkflow<PropsT extends java.lang.Object, StateT extends java.lang.Object, OutputT extends com.github.michaelbull.result.Result<?, ?>> extends com.squareup.workflow.StatefulWorkflow<PropsT, StateT, OutputT, com.wclausen.work.command.base.Command> implements com.wclausen.work.command.base.CommandWorkflow<PropsT, OutputT> {
    
    public StatefulCommandWorkflow() {
        super();
    }
}