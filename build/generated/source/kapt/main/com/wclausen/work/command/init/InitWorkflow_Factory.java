package com.wclausen.work.command.init;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class InitWorkflow_Factory implements Factory<InitWorkflow> {
  @Override
  public InitWorkflow get() {
    return newInstance();
  }

  public static InitWorkflow_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InitWorkflow newInstance() {
    return new InitWorkflow();
  }

  private static final class InstanceHolder {
    private static final InitWorkflow_Factory INSTANCE = new InitWorkflow_Factory();
  }
}
