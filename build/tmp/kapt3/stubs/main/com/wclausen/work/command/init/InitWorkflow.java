package com.wclausen.work.command.init;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0001:\u0003\u0015\u0016\u0017B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0007J\u001f\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016\u00a2\u0006\u0002\u0010\rJ=\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00032\u001e\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0012H\u0016\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow;", "Lcom/wclausen/work/command/base/StatefulCommandWorkflow;", "", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/config/Config;", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationError;", "()V", "config", "initialState", "props", "snapshot", "Lcom/squareup/workflow/Snapshot;", "(Lkotlin/Unit;Lcom/squareup/workflow/Snapshot;)Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;", "render", "Lcom/wclausen/work/command/base/Command;", "state", "context", "Lcom/squareup/workflow/RenderContext;", "(Lkotlin/Unit;Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;Lcom/squareup/workflow/RenderContext;)Lcom/wclausen/work/command/base/Command;", "snapshotState", "Companion", "InitializationError", "InitializationState", "work-cli"})
public final class InitWorkflow extends com.wclausen.work.command.base.StatefulCommandWorkflow<kotlin.Unit, com.wclausen.work.command.init.InitWorkflow.InitializationState, com.github.michaelbull.result.Result<? extends com.wclausen.work.config.Config, ? extends com.wclausen.work.command.init.InitWorkflow.InitializationError>> {
    private com.wclausen.work.config.Config config;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USERNAME_PROMPT = "Please enter your jira username (e.g. wclausen@dropbox.com)";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_JIRA_API_TOKEN_PROMPT = "Please enter your jira api token";
    public static final com.wclausen.work.command.init.InitWorkflow.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.init.InitWorkflow.InitializationState initialState(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.Nullable()
    com.squareup.workflow.Snapshot snapshot) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.base.Command render(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.init.InitWorkflow.InitializationState state, @org.jetbrains.annotations.NotNull()
    com.squareup.workflow.RenderContext<com.wclausen.work.command.init.InitWorkflow.InitializationState, ? super com.github.michaelbull.result.Result<com.wclausen.work.config.Config, com.wclausen.work.command.init.InitWorkflow.InitializationError>> context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.workflow.Snapshot snapshotState(@org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.init.InitWorkflow.InitializationState state) {
        return null;
    }
    
    @javax.inject.Inject()
    public InitWorkflow() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;", "", "()V", "GetPassword", "GetUsername", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationState$GetUsername;", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationState$GetPassword;", "work-cli"})
    public static abstract class InitializationState {
        
        private InitializationState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow$InitializationState$GetUsername;", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;", "()V", "work-cli"})
        public static final class GetUsername extends com.wclausen.work.command.init.InitWorkflow.InitializationState {
            public static final com.wclausen.work.command.init.InitWorkflow.InitializationState.GetUsername INSTANCE = null;
            
            private GetUsername() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow$InitializationState$GetPassword;", "Lcom/wclausen/work/command/init/InitWorkflow$InitializationState;", "()V", "work-cli"})
        public static final class GetPassword extends com.wclausen.work.command.init.InitWorkflow.InitializationState {
            public static final com.wclausen.work.command.init.InitWorkflow.InitializationState.GetPassword INSTANCE = null;
            
            private GetPassword() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow$InitializationError;", "", "()V", "work-cli"})
    public static final class InitializationError {
        
        public InitializationError() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/wclausen/work/command/init/InitWorkflow$Companion;", "", "()V", "GET_JIRA_API_TOKEN_PROMPT", "", "GET_USERNAME_PROMPT", "work-cli"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}