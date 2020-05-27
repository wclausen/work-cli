package com.wclausen.work.command.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0010\b\u0001\u0010\u0002*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/wclausen/work/command/base/CommandWorkflow;", "PropsT", "OutputT", "Lcom/github/michaelbull/result/Result;", "Lcom/squareup/workflow/Workflow;", "Lcom/wclausen/work/command/base/Command;", "work-cli"})
public abstract interface CommandWorkflow<PropsT extends java.lang.Object, OutputT extends com.github.michaelbull.result.Result<?, ?>> extends com.squareup.workflow.Workflow<PropsT, OutputT, com.wclausen.work.command.base.Command> {
}