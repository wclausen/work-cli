package com.wclausen.work.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016J\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006H\u0002J\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/wclausen/work/config/RealConfigReader;", "Lcom/wclausen/work/config/ConfigReader;", "configFile", "Ljava/io/File;", "(Ljava/io/File;)V", "getConfigFields", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/config/Config;", "Lcom/wclausen/work/config/ConfigLoadingError;", "openFile", "Lokio/BufferedSource;", "parseJson", "json", "", "readLines", "bufferedSource", "Companion", "work-cli"})
public final class RealConfigReader implements com.wclausen.work.config.ConfigReader {
    private final java.io.File configFile = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE = "Failed to open file";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAILED_TO_READ_CONFIG_MESSAGE = "Failed to read lines from com.wclausen.work.config";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAILED_TO_PARSE_JSON_MESSAGE = "Failed to parse com.wclausen.work.config data";
    public static final com.wclausen.work.config.RealConfigReader.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.michaelbull.result.Result<com.wclausen.work.config.Config, com.wclausen.work.config.ConfigLoadingError> getConfigFields() {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<okio.BufferedSource, com.wclausen.work.config.ConfigLoadingError> openFile() {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<java.lang.String, com.wclausen.work.config.ConfigLoadingError> readLines(okio.BufferedSource bufferedSource) {
        return null;
    }
    
    private final com.github.michaelbull.result.Result<com.wclausen.work.config.Config, com.wclausen.work.config.ConfigLoadingError> parseJson(java.lang.String json) {
        return null;
    }
    
    @javax.inject.Inject()
    public RealConfigReader(@org.jetbrains.annotations.NotNull()
    java.io.File configFile) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/wclausen/work/config/RealConfigReader$Companion;", "", "()V", "FAILED_TO_PARSE_JSON_MESSAGE", "", "FAILED_TO_READ_CONFIG_MESSAGE", "UNABLE_TO_OPEN_CONFIG_FILE_MESSAGE", "work-cli"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}