package com.wclausen.work.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0005H\u0016J$\u0010\u0019\u001a\u0016\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00160\u0016\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001b\u001a\u00020\tH\u0002R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\t8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/wclausen/work/base/WrapperCommand;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "onResult", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "arguments", "", "", "getArguments", "()Ljava/util/List;", "arguments$delegate", "Lkotlin/properties/ReadOnlyProperty;", "command", "getCommand", "()Ljava/lang/String;", "command$delegate", "getExitCode", "Lcom/github/michaelbull/result/Result;", "", "process", "Ljava/lang/Process;", "printResults", "run", "startProcess", "kotlin.jvm.PlatformType", "cmd", "work-cli"})
public final class WrapperCommand extends com.github.ajalt.clikt.core.CliktCommand {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.properties.ReadOnlyProperty command$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.properties.ReadOnlyProperty arguments$delegate = null;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onResult = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCommand() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getArguments() {
        return null;
    }
    
    @java.lang.Override()
    public void run() {
    }
    
    private final com.github.michaelbull.result.Result<java.lang.Integer, java.lang.Throwable> getExitCode(java.lang.Process process) {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<java.lang.Process, java.lang.Throwable> printResults(java.lang.Process process) {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<java.lang.Process, java.lang.Throwable> startProcess(java.lang.String cmd) {
        return null;
    }
    
    public WrapperCommand(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onResult) {
        super(null, null, null, false, false, null, null, false);
    }
}