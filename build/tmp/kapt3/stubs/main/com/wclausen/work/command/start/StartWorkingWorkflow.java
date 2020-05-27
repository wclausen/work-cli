package com.wclausen.work.command.start;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 12 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0001:\u0003123B\u001f\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ0\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002J6\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0004H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016\u00a2\u0006\u0002\u0010\u001fJ(\u0010 \u001a\u00020!2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J0\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00162\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J6\u0010$\u001a\u00020!2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J6\u0010%\u001a\u00020!2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0002J=\u0010&\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\'\u001a\u00020\u00032\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0010H\u0016\u00a2\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020\bH\u0002J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010\'\u001a\u00020\u0003H\u0016J$\u0010,\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010.\u001a\u00020\b2\u0006\u0010/\u001a\u000200H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow;", "Lcom/wclausen/work/command/base/StatefulCommandWorkflow;", "", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/base/WorkflowState;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError;", "taskId", "", "jiraService", "Lcom/wclausen/work/jira/JiraService;", "gitService", "Lcom/wclausen/work/git/GitService;", "(Ljava/lang/String;Lcom/wclausen/work/jira/JiraService;Lcom/wclausen/work/git/GitService;)V", "checkoutBranch", "context", "Lcom/squareup/workflow/RenderContext;", "checkoutGitBranchInBackground", "getTaskSelectionPromptCommand", "Lcom/wclausen/work/command/base/Command$Prompt;", "tasks", "", "Lcom/wclausen/work/jira/api/model/IssueBean;", "getTasksFromJira", "Lcom/wclausen/work/jira/api/model/JqlSearchResult;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$LoadTasksError;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialState", "props", "snapshot", "Lcom/squareup/workflow/Snapshot;", "(Lkotlin/Unit;Lcom/squareup/workflow/Snapshot;)Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "loadTasks", "Lcom/wclausen/work/command/base/Command;", "promptForTaskGoal", "selectedTask", "promptForTaskSelection", "promptForTaskSelectionAfterError", "render", "state", "(Lkotlin/Unit;Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;Lcom/squareup/workflow/RenderContext;)Lcom/wclausen/work/command/base/Command;", "showSelectedTask", "selectedTaskId", "snapshotState", "validateSelection", "", "selectedTaskString", "selectionRange", "Lkotlin/ranges/IntRange;", "Companion", "StartTaskError", "StartTaskState", "work-cli"})
public final class StartWorkingWorkflow extends com.wclausen.work.command.base.StatefulCommandWorkflow<kotlin.Unit, com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> {
    private final java.lang.String taskId = null;
    private final com.wclausen.work.jira.JiraService jiraService = null;
    private final com.wclausen.work.git.GitService gitService = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOADING_TASKS_MESSAGE = "Loading tasks from jira...";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOADING_TASKS_FAILED_MESSAGE = "Failed when loading jira tasks for user";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TASK_SELECTION_FAILED_MESSAGE = "Selected task is not within bounds";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GIT_BRANCH_CHECKOUT_FAILED_MESSAGE = "Failed to checkout branch for task";
    public static final com.wclausen.work.command.start.StartWorkingWorkflow.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState initialState(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.Nullable()
    com.squareup.workflow.Snapshot snapshot) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wclausen.work.command.base.Command render(@org.jetbrains.annotations.NotNull()
    kotlin.Unit props, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState state, @org.jetbrains.annotations.NotNull()
    com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    private final void checkoutBranch(java.lang.String taskId, com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
    }
    
    private final com.wclausen.work.command.base.Command promptForTaskGoal(com.wclausen.work.jira.api.model.IssueBean selectedTask, com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    private final void checkoutGitBranchInBackground(java.lang.String taskId) {
    }
    
    private final com.wclausen.work.command.base.Command showSelectedTask(java.lang.String selectedTaskId) {
        return null;
    }
    
    private final com.wclausen.work.command.base.Command promptForTaskSelectionAfterError(java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks, com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    private final com.wclausen.work.command.base.Command promptForTaskSelection(java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks, com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    private final com.wclausen.work.command.base.Command.Prompt getTaskSelectionPromptCommand(java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks, com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<java.lang.Integer, com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError> validateSelection(java.lang.String selectedTaskString, kotlin.ranges.IntRange selectionRange) {
        return null;
    }
    
    private final com.wclausen.work.command.base.Command loadTasks(com.squareup.workflow.RenderContext<com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState, ? super com.github.michaelbull.result.Result<? extends com.wclausen.work.base.WorkflowState, ? extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError>> context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.workflow.Snapshot snapshotState(@org.jetbrains.annotations.NotNull()
    com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState state) {
        return null;
    }
    
    public StartWorkingWorkflow(@org.jetbrains.annotations.Nullable()
    java.lang.String taskId, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.jira.JiraService jiraService, @org.jetbrains.annotations.NotNull()
    com.wclausen.work.git.GitService gitService) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "", "()V", "BranchCheckedOut", "Error", "GoalProvided", "InvalidTaskSelected", "LoadTasks", "TaskSelected", "TasksLoaded", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$LoadTasks;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$TasksLoaded;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$InvalidTaskSelected;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$TaskSelected;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$GoalProvided;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$BranchCheckedOut;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$Error;", "work-cli"})
    public static abstract class StartTaskState {
        
        private StartTaskState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$LoadTasks;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "()V", "work-cli"})
        public static final class LoadTasks extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            
            public LoadTasks() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$TasksLoaded;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "tasks", "", "Lcom/wclausen/work/jira/api/model/IssueBean;", "(Ljava/util/List;)V", "getTasks", "()Ljava/util/List;", "work-cli"})
        public static final class TasksLoaded extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<com.wclausen.work.jira.api.model.IssueBean> getTasks() {
                return null;
            }
            
            public TasksLoaded(@org.jetbrains.annotations.NotNull()
            java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$InvalidTaskSelected;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "tasks", "", "Lcom/wclausen/work/jira/api/model/IssueBean;", "(Ljava/util/List;)V", "getTasks", "()Ljava/util/List;", "work-cli"})
        public static final class InvalidTaskSelected extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<com.wclausen.work.jira.api.model.IssueBean> getTasks() {
                return null;
            }
            
            public InvalidTaskSelected(@org.jetbrains.annotations.NotNull()
            java.util.List<com.wclausen.work.jira.api.model.IssueBean> tasks) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$TaskSelected;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "selectedTask", "Lcom/wclausen/work/jira/api/model/IssueBean;", "(Lcom/wclausen/work/jira/api/model/IssueBean;)V", "getSelectedTask", "()Lcom/wclausen/work/jira/api/model/IssueBean;", "work-cli"})
        public static final class TaskSelected extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final com.wclausen.work.jira.api.model.IssueBean selectedTask = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.wclausen.work.jira.api.model.IssueBean getSelectedTask() {
                return null;
            }
            
            public TaskSelected(@org.jetbrains.annotations.NotNull()
            com.wclausen.work.jira.api.model.IssueBean selectedTask) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$GoalProvided;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "selectedTask", "Lcom/wclausen/work/jira/api/model/IssueBean;", "(Lcom/wclausen/work/jira/api/model/IssueBean;)V", "getSelectedTask", "()Lcom/wclausen/work/jira/api/model/IssueBean;", "work-cli"})
        public static final class GoalProvided extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final com.wclausen.work.jira.api.model.IssueBean selectedTask = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.wclausen.work.jira.api.model.IssueBean getSelectedTask() {
                return null;
            }
            
            public GoalProvided(@org.jetbrains.annotations.NotNull()
            com.wclausen.work.jira.api.model.IssueBean selectedTask) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$BranchCheckedOut;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "branchName", "", "(Ljava/lang/String;)V", "getBranchName", "()Ljava/lang/String;", "work-cli"})
        public static final class BranchCheckedOut extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String branchName = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getBranchName() {
                return null;
            }
            
            public BranchCheckedOut(@org.jetbrains.annotations.NotNull()
            java.lang.String branchName) {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState$Error;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskState;", "userMessage", "", "(Ljava/lang/String;)V", "getUserMessage", "()Ljava/lang/String;", "work-cli"})
        public static final class Error extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userMessage = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserMessage() {
                return null;
            }
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String userMessage) {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0005\u0082\u0001\u0003\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError;", "", "message", "", "throwable", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "CheckoutBranchError", "LoadTasksError", "TaskSelectionError", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$LoadTasksError;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$TaskSelectionError;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$CheckoutBranchError;", "work-cli"})
    public static abstract class StartTaskError extends java.lang.Throwable {
        
        private StartTaskError(java.lang.String message, java.lang.Throwable throwable) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$LoadTasksError;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError;", "throwable", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class LoadTasksError extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError {
            
            public LoadTasksError(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable throwable) {
                super(null, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$TaskSelectionError;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError;", "throwable", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class TaskSelectionError extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError {
            
            public TaskSelectionError(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable throwable) {
                super(null, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError$CheckoutBranchError;", "Lcom/wclausen/work/command/start/StartWorkingWorkflow$StartTaskError;", "cause", "Lcom/wclausen/work/git/GitService$GitError;", "(Lcom/wclausen/work/git/GitService$GitError;)V", "work-cli"})
        public static final class CheckoutBranchError extends com.wclausen.work.command.start.StartWorkingWorkflow.StartTaskError {
            
            public CheckoutBranchError(@org.jetbrains.annotations.NotNull()
            com.wclausen.work.git.GitService.GitError cause) {
                super(null, null);
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/wclausen/work/command/start/StartWorkingWorkflow$Companion;", "", "()V", "GIT_BRANCH_CHECKOUT_FAILED_MESSAGE", "", "LOADING_TASKS_FAILED_MESSAGE", "LOADING_TASKS_MESSAGE", "TASK_SELECTION_FAILED_MESSAGE", "work-cli"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}