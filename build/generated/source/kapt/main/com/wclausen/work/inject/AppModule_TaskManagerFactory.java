package com.wclausen.work.inject;

import com.wclausen.work.task.TaskManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_TaskManagerFactory implements Factory<TaskManager> {
  private final AppModule module;

  public AppModule_TaskManagerFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public TaskManager get() {
    return taskManager(module);
  }

  public static AppModule_TaskManagerFactory create(AppModule module) {
    return new AppModule_TaskManagerFactory(module);
  }

  public static TaskManager taskManager(AppModule instance) {
    return Preconditions.checkNotNull(instance.taskManager(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
