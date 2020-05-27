package com.wclausen.work.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/wclausen/work/config/ConfigLoadingError;", "", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "NoConfigFileError", "ParsingFileError", "Lcom/wclausen/work/config/ConfigLoadingError$NoConfigFileError;", "Lcom/wclausen/work/config/ConfigLoadingError$ParsingFileError;", "work-cli"})
public abstract class ConfigLoadingError {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    private ConfigLoadingError(java.lang.String message) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/config/ConfigLoadingError$NoConfigFileError;", "Lcom/wclausen/work/config/ConfigLoadingError;", "message", "", "(Ljava/lang/String;)V", "work-cli"})
    public static final class NoConfigFileError extends com.wclausen.work.config.ConfigLoadingError {
        
        public NoConfigFileError(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/config/ConfigLoadingError$ParsingFileError;", "Lcom/wclausen/work/config/ConfigLoadingError;", "message", "", "(Ljava/lang/String;)V", "work-cli"})
    public static final class ParsingFileError extends com.wclausen.work.config.ConfigLoadingError {
        
        public ParsingFileError(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            super(null);
        }
    }
}