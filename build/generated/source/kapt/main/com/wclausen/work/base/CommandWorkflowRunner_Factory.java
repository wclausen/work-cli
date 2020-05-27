package com.wclausen.work.base;

import com.github.michaelbull.result.Result;
import com.wclausen.work.command.base.CommandWorkflow;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.channels.BroadcastChannel;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CommandWorkflowRunner_Factory<PropsT, OutputT extends Result<?, ?>> implements Factory<CommandWorkflowRunner<PropsT, OutputT>> {
  private final Provider<BroadcastChannel<PropsT>> inputsProvider;

  private final Provider<CommandWorkflow<? super PropsT, OutputT>> commandWorkflowProvider;

  public CommandWorkflowRunner_Factory(Provider<BroadcastChannel<PropsT>> inputsProvider,
      Provider<CommandWorkflow<? super PropsT, OutputT>> commandWorkflowProvider) {
    this.inputsProvider = inputsProvider;
    this.commandWorkflowProvider = commandWorkflowProvider;
  }

  @Override
  public CommandWorkflowRunner<PropsT, OutputT> get() {
    return newInstance(inputsProvider.get(), commandWorkflowProvider.get());
  }

  public static <PropsT, OutputT extends Result<?, ?>> CommandWorkflowRunner_Factory<PropsT, OutputT> create(
      Provider<BroadcastChannel<PropsT>> inputsProvider,
      Provider<CommandWorkflow<? super PropsT, OutputT>> commandWorkflowProvider) {
    return new CommandWorkflowRunner_Factory<PropsT, OutputT>(inputsProvider, commandWorkflowProvider);
  }

  public static <PropsT, OutputT extends Result<?, ?>> CommandWorkflowRunner<PropsT, OutputT> newInstance(
      BroadcastChannel<PropsT> inputs, CommandWorkflow<? super PropsT, OutputT> commandWorkflow) {
    return new CommandWorkflowRunner<PropsT, OutputT>(inputs, commandWorkflow);
  }
}
