package com.wclausen.work.command.update;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0001:\u0002 !B\u001d\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ0\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\b2\u001e\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0013H\u0002J\u001f\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016\u00a2\u0006\u0002\u0010\u0018J=\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00032\u001e\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0013H\u0016\u00a2\u0006\u0002\u0010\u001cJ0\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\b2\u001e\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0003H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow;", "Lcom/wclausen/work/command/base/StatefulCommandWorkflow;", "", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/base/WorkflowState;", "Lcom/wclausen/work/command/update/UpdateWorkflow$Error;", "issueId", "", "jiraService", "Lcom/wclausen/work/jira/JiraService;", "gitService", "Lcom/wclausen/work/git/GitService;", "(Ljava/lang/String;Lcom/wclausen/work/jira/JiraService;Lcom/wclausen/work/git/GitService;)V", "responsesCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "commit", "message", "context", "Lcom/squareup/workflow/RenderContext;", "initialState", "props", "snapshot", "Lcom/squareup/workflow/Snapshot;", "(Lkotlin/Unit;Lcom/squareup/workflow/Snapshot;)Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "render", "Lcom/wclausen/work/command/base/Command;", "state", "(Lkotlin/Unit;Lcom/wclausen/work/command/update/UpdateWorkflow$State;Lcom/squareup/workflow/RenderContext;)Lcom/wclausen/work/command/base/Command;", "sendCommentToJira", "comment", "snapshotState", "Error", "State", "work-cli"})
public final class UpdateWorkflow extends com.wclausen.work.command.base.StatefulCommandWorkflow<kotlin.Unit, com.wclausen.work.command.update.UpdateWorkflow.State, com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.update.UpdateWorkflow.Error>> {
    private java.util.concurrent.atomic.AtomicInteger responsesCounter;
    private final java.lang.String issueId = null;
    private final com.wclausen.work.jira.JiraService jiraService = null;
    private final com.wclausen.work.git.GitService gitService = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.update.UpdateWorkflow.State initialState(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.Nullable()
    com.squareup.workflow.Snapshot snapshot) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.base.Command render(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.update.UpdateWorkflow.State state, @org.jetbrains.annotations.NotNull()
    com.squareup.workflow.RenderContext<com.wclausen.work.command.update.UpdateWorkflow.State, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.update.UpdateWorkflow.Error>> context) {
        return null;
    }
    
    private final void commit(java.lang.String message, com.squareup.workflow.RenderContext<com.wclausen.work.command.update.UpdateWorkflow.State, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.update.UpdateWorkflow.Error>> context) {
    }
    
    private final void sendCommentToJira(java.lang.String comment, com.squareup.workflow.RenderContext<com.wclausen.work.command.update.UpdateWorkflow.State, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.update.UpdateWorkflow.Error>> context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.workflow.Snapshot snapshotState(@org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.update.UpdateWorkflow.State state) {
        return null;
    }
    
    public UpdateWorkflow(@org.jetbrains.annotations.NotNull()
    java.lang.String issueId, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.jira.JiraService jiraService, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.git.GitService gitService) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0005\u0082\u0001\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$Error;", "", "message", "", "cause", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "GitCommitFailed", "JiraFailedToUpdate", "Lcom/wclausen/work/command/update/UpdateWorkflow$Error$JiraFailedToUpdate;", "Lcom/wclausen/work/command/update/UpdateWorkflow$Error$GitCommitFailed;", "work-cli"})
    public static abstract class Error extends java.lang.Throwable {
        
        private Error(java.lang.String message, java.lang.Throwable cause) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$Error$JiraFailedToUpdate;", "Lcom/wclausen/work/command/update/UpdateWorkflow$Error;", "cause", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class JiraFailedToUpdate extends com.wclausen.work.command.update.UpdateWorkflow.Error {
            
            public JiraFailedToUpdate(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable cause) {
                super(null, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$Error$GitCommitFailed;", "Lcom/wclausen/work/command/update/UpdateWorkflow$Error;", "cause", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class GitCommitFailed extends com.wclausen.work.command.update.UpdateWorkflow.Error {
            
            public GitCommitFailed(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable cause) {
                super(null, null);
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "", "()V", "Error", "FinishedBackgroundWork", "GetGitCommit", "GetJiraComment", "GitFinishedUpdating", "JiraFinishedUpdating", "Waiting", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$GetJiraComment;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$GetGitCommit;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$Waiting;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$GitFinishedUpdating;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$JiraFinishedUpdating;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$Error;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State$FinishedBackgroundWork;", "work-cli"})
    public static abstract class State {
        
        private State() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$GetJiraComment;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "()V", "work-cli"})
        public static final class GetJiraComment extends com.wclausen.work.command.update.UpdateWorkflow.State {
            
            public GetJiraComment() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$GetGitCommit;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "jiraComment", "", "(Ljava/lang/String;)V", "getJiraComment", "()Ljava/lang/String;", "work-cli"})
        public static final class GetGitCommit extends com.wclausen.work.command.update.UpdateWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String jiraComment = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getJiraComment() {
                return null;
            }
            
            public GetGitCommit(@org.jetbrains.annotations.NotNull()
            java.lang.String jiraComment) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$Waiting;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "commitMsg", "", "(Ljava/lang/String;)V", "getCommitMsg", "()Ljava/lang/String;", "work-cli"})
        public static final class Waiting extends com.wclausen.work.command.update.UpdateWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String commitMsg = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getCommitMsg() {
                return null;
            }
            
            public Waiting(@org.jetbrains.annotations.NotNull()
            java.lang.String commitMsg) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$GitFinishedUpdating;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "()V", "work-cli"})
        public static final class GitFinishedUpdating extends com.wclausen.work.command.update.UpdateWorkflow.State {
            
            public GitFinishedUpdating() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$JiraFinishedUpdating;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "()V", "work-cli"})
        public static final class JiraFinishedUpdating extends com.wclausen.work.command.update.UpdateWorkflow.State {
            
            public JiraFinishedUpdating() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$Error;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "work-cli"})
        public static final class Error extends com.wclausen.work.command.update.UpdateWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/update/UpdateWorkflow$State$FinishedBackgroundWork;", "Lcom/wclausen/work/command/update/UpdateWorkflow$State;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "work-cli"})
        public static final class FinishedBackgroundWork extends com.wclausen.work.command.update.UpdateWorkflow.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public FinishedBackgroundWork(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                super();
            }
        }
    }
}