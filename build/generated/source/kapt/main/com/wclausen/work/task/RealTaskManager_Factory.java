package com.wclausen.work.task;

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
public final class RealTaskManager_Factory implements Factory<RealTaskManager> {
  @Override
  public RealTaskManager get() {
    return newInstance();
  }

  public static RealTaskManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RealTaskManager newInstance() {
    return new RealTaskManager();
  }

  private static final class InstanceHolder {
    private static final RealTaskManager_Factory INSTANCE = new RealTaskManager_Factory();
  }
}
