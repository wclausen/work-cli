package com.wclausen.work.config;

import java.lang.System;

/**
 * Class that enables creation of multiple types of configs
 *
 * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
 * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
 * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
 * serializing/deserializing configs of multiple types.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0004H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u00c6\u0003J)\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/wclausen/work/config/ConfigInfo;", "T", "", "configFile", "Ljava/io/File;", "configClazz", "Lkotlin/reflect/KClass;", "(Ljava/io/File;Lkotlin/reflect/KClass;)V", "getConfigClazz", "()Lkotlin/reflect/KClass;", "getConfigFile", "()Ljava/io/File;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "work-cli"})
public final class ConfigInfo<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull()
    private final java.io.File configFile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.reflect.KClass<T> configClazz = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getConfigFile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.reflect.KClass<T> getConfigClazz() {
        return null;
    }
    
    public ConfigInfo(@org.jetbrains.annotations.NotNull()
    java.io.File configFile, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<T> configClazz) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.reflect.KClass<T> component2() {
        return null;
    }
    
    /**
     * Class that enables creation of multiple types of configs
     *
     * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
     * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
     * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
     * serializing/deserializing configs of multiple types.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.wclausen.work.config.ConfigInfo<T> copy(@org.jetbrains.annotations.NotNull()
    java.io.File configFile, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<T> configClazz) {
        return null;
    }
    
    /**
     * Class that enables creation of multiple types of configs
     *
     * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
     * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
     * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
     * serializing/deserializing configs of multiple types.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * Class that enables creation of multiple types of configs
     *
     * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
     * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
     * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
     * serializing/deserializing configs of multiple types.
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Class that enables creation of multiple types of configs
     *
     * Currently, there's only one type of com.wclausen.work.config, a global [Config] for the CLI, but
     * it seemed possible that the CLI could manage multiple types of configs (like a com.wclausen.work.config
     * for Jira info and a com.wclausen.work.config for Git repo info), so this class is used to support
     * serializing/deserializing configs of multiple types.
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}