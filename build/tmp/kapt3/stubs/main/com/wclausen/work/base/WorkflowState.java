package com.wclausen.work.base;

import java.lang.System;

/**
 * Meant to encapsulate the highest level of state in the system.
 *
 * This state determines what commands are valid at startup. For example, if a user tries to run
 * `work start` from an [WorkflowState.Uninitialized] then they should be prompted to first
 * initialize the CLI tool (provide jira creds, specify git repo, etc).
 *
 * TODO: actually use this state to determine valid actions, persist across invocations of CLI
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/wclausen/work/base/WorkflowState;", "", "()V", "Executing", "Uninitialized", "Waiting", "Lcom/wclausen/work/base/WorkflowState$Uninitialized;", "Lcom/wclausen/work/base/WorkflowState$Waiting;", "Lcom/wclausen/work/base/WorkflowState$Executing;", "work-cli"})
public abstract class WorkflowState {
    
    private WorkflowState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/base/WorkflowState$Uninitialized;", "Lcom/wclausen/work/base/WorkflowState;", "()V", "work-cli"})
    public static final class Uninitialized extends com.wclausen.work.base.WorkflowState {
        public static final com.wclausen.work.base.WorkflowState.Uninitialized INSTANCE = null;
        
        private Uninitialized() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wclausen/work/base/WorkflowState$Waiting;", "Lcom/wclausen/work/base/WorkflowState;", "()V", "work-cli"})
    public static final class Waiting extends com.wclausen.work.base.WorkflowState {
        public static final com.wclausen.work.base.WorkflowState.Waiting INSTANCE = null;
        
        private Waiting() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/base/WorkflowState$Executing;", "Lcom/wclausen/work/base/WorkflowState;", "taskId", "", "(Ljava/lang/String;)V", "getTaskId", "()Ljava/lang/String;", "work-cli"})
    public static final class Executing extends com.wclausen.work.base.WorkflowState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String taskId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTaskId() {
            return null;
        }
        
        public Executing(@org.jetbrains.annotations.NotNull()
        java.lang.String taskId) {
            super();
        }
    }
}