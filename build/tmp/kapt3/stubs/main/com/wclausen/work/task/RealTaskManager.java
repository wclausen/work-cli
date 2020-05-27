package com.wclausen.work.task;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/task/RealTaskManager;", "Lcom/wclausen/work/task/TaskManager;", "()V", "getCurrentTask", "", "hasCurrentTask", "", "work-cli"})
public final class RealTaskManager implements com.wclausen.work.task.TaskManager {
    
    @java.lang.Override()
    public boolean hasCurrentTask() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getCurrentTask() {
        return null;
    }
    
    @javax.inject.Inject()
    public RealTaskManager() {
        super();
    }
}