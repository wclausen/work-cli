package com.wclausen.work.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/wclausen/work/config/ConfigCreator;", "", "createConfig", "", "config", "Lcom/wclausen/work/config/Config;", "work-cli"})
public abstract interface ConfigCreator {
    
    public abstract void createConfig(@org.jetbrains.annotations.NotNull()
    com.wclausen.work.config.Config config);
}