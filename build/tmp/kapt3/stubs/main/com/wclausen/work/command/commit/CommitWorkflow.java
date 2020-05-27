package com.wclausen.work.command.commit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0001:\u0002\u0018\u0019B\u0015\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0002\u0010\u0010J=\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00032\u001e\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0015H\u0016\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow;", "Lcom/wclausen/work/command/base/StatefulCommandWorkflow;", "", "Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "Lcom/github/michaelbull/result/Result;", "Lorg/eclipse/jgit/revwalk/RevCommit;", "Lcom/wclausen/work/command/commit/CommitWorkflow$Error;", "currentTask", "", "gitService", "Lcom/wclausen/work/git/GitService;", "(Ljava/lang/String;Lcom/wclausen/work/git/GitService;)V", "initialState", "props", "snapshot", "Lcom/squareup/workflow/Snapshot;", "(Lkotlin/Unit;Lcom/squareup/workflow/Snapshot;)Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "render", "Lcom/wclausen/work/command/base/Command;", "state", "context", "Lcom/squareup/workflow/RenderContext;", "(Lkotlin/Unit;Lcom/wclausen/work/command/commit/CommitWorkflow$State;Lcom/squareup/workflow/RenderContext;)Lcom/wclausen/work/command/base/Command;", "snapshotState", "Error", "State", "work-cli"})
public final class CommitWorkflow extends com.wclausen.work.command.base.StatefulCommandWorkflow<kotlin.Unit, com.wclausen.work.command.commit.CommitWorkflow.State, com.github.michaelbull.result.Result<? extends org.eclipse.jgit.revwalk.RevCommit, ? extends com.wclausen.work.command.commit.CommitWorkflow.Error>> {
    private final java.lang.String currentTask = null;
    private final com.wclausen.work.git.GitService gitService = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.commit.CommitWorkflow.State initialState(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.Nullable()
    com.squareup.workflow.Snapshot snapshot) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.base.Command render(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.commit.CommitWorkflow.State state, @org.jetbrains.annotations.NotNull()
    com.squareup.workflow.RenderContext<com.wclausen.work.command.commit.CommitWorkflow.State, ? super com.github.michaelbull.result.Result<? extends org.eclipse.jgit.revwalk.RevCommit, ? extends com.wclausen.work.command.commit.CommitWorkflow.Error>> context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.workflow.Snapshot snapshotState(@org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.commit.CommitWorkflow.State state) {
        return null;
    }
    
    public CommitWorkflow(@org.jetbrains.annotations.NotNull()
    java.lang.String currentTask, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.git.GitService gitService) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "", "()V", "Finished", "PromptForCommitMessage", "ReceivedCommitMessage", "Lcom/wclausen/work/command/commit/CommitWorkflow$State$PromptForCommitMessage;", "Lcom/wclausen/work/command/commit/CommitWorkflow$State$ReceivedCommitMessage;", "Lcom/wclausen/work/command/commit/CommitWorkflow$State$Finished;", "work-cli"})
    public static abstract class State {
        
        private State() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$State$PromptForCommitMessage;", "Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "()V", "work-cli"})
        public static final class PromptForCommitMessage extends com.wclausen.work.command.commit.CommitWorkflow.State {
            
            public PromptForCommitMessage() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$State$ReceivedCommitMessage;", "Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "work-cli"})
        public static final class ReceivedCommitMessage extends com.wclausen.work.command.commit.CommitWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public ReceivedCommitMessage(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$State$Finished;", "Lcom/wclausen/work/command/commit/CommitWorkflow$State;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "work-cli"})
        public static final class Finished extends com.wclausen.work.command.commit.CommitWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public Finished(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0006B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0005\u0082\u0001\u0001\u0007\u00a8\u0006\b"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$Error;", "", "message", "", "cause", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "CommitFailedError", "Lcom/wclausen/work/command/commit/CommitWorkflow$Error$CommitFailedError;", "work-cli"})
    public static abstract class Error extends java.lang.Throwable {
        
        private Error(java.lang.String message, java.lang.Throwable cause) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/commit/CommitWorkflow$Error$CommitFailedError;", "Lcom/wclausen/work/command/commit/CommitWorkflow$Error;", "cause", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class CommitFailedError extends com.wclausen.work.command.commit.CommitWorkflow.Error {
            
            public CommitFailedError(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable cause) {
                super(null, null);
            }
        }
    }
}