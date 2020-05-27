package com.wclausen.work.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H&\u00a8\u0006\u0006"}, d2 = {"Lcom/wclausen/work/config/ConfigReader;", "", "getConfigFields", "Lcom/github/michaelbull/result/Result;", "Lcom/wclausen/work/config/Config;", "Lcom/wclausen/work/config/ConfigLoadingError;", "work-cli"})
public abstract interface ConfigReader {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.github.michaelbull.result.Result<com.wclausen.work.config.Config, com.wclausen.work.config.ConfigLoadingError> getConfigFields();
}