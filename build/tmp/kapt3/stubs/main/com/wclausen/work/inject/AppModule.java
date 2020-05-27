package com.wclausen.work.inject;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007\u00a2\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0007R\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/wclausen/work/inject/AppModule;", "", "args", "", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "configFile", "Ljava/io/File;", "configReader", "Lcom/wclausen/work/config/ConfigReader;", "mainArgs", "()[Ljava/lang/String;", "taskManager", "Lcom/wclausen/work/task/TaskManager;", "work-cli"})
@dagger.Module()
public final class AppModule {
    private final java.lang.String[] args = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final java.lang.String[] mainArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.wclausen.work.task.TaskManager taskManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final java.io.File configFile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final com.wclausen.work.config.ConfigReader configReader(@org.jetbrains.annotations.NotNull()
    java.io.File configFile) {
        return null;
    }
    
    public AppModule(@org.jetbrains.annotations.NotNull()
    java.lang.String[] args) {
        super();
    }
}