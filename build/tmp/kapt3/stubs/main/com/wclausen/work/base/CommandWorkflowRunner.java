package com.wclausen.work.base;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0010\b\u0001\u0010\u0002*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u00020\u0004B)\b\u0007\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\r\u0010\u000e\u001a\u00028\u0001H\u0007\u00a2\u0006\u0002\u0010\u000fR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0088\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/wclausen/work/base/CommandWorkflowRunner;", "PropsT", "OutputT", "Lcom/github/michaelbull/result/Result;", "", "inputs", "Lkotlinx/coroutines/channels/BroadcastChannel;", "commandWorkflow", "Lcom/wclausen/work/command/base/CommandWorkflow;", "(Lkotlinx/coroutines/channels/BroadcastChannel;Lcom/wclausen/work/command/base/CommandWorkflow;)V", "handleCommand", "", "rendering", "Lcom/wclausen/work/command/base/Command;", "run", "()Lcom/github/michaelbull/result/Result;", "work-cli"})
public final class CommandWorkflowRunner<PropsT extends java.lang.Object, OutputT extends com.github.michaelbull.result.Result<?, ?>> {
    private final kotlinx.coroutines.channels.BroadcastChannel<PropsT> inputs = null;
    private final com.wclausen.work.command.base.CommandWorkflow<PropsT, OutputT> commandWorkflow = null;
    
    @org.jetbrains.annotations.NotNull()
    @kotlinx.coroutines.FlowPreview()
    public final OutputT run() {
        return null;
    }
    
    private final void handleCommand(com.wclausen.work.command.base.Command rendering) {
    }
    
    @javax.inject.Inject()
    public CommandWorkflowRunner(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.channels.BroadcastChannel<PropsT> inputs, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.base.CommandWorkflow<? super PropsT, OutputT> commandWorkflow) {
        super();
    }
}