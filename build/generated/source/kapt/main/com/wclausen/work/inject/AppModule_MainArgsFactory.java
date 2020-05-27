package com.wclausen.work.inject;

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
public final class AppModule_MainArgsFactory implements Factory<String[]> {
  private final AppModule module;

  public AppModule_MainArgsFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public String[] get() {
    return mainArgs(module);
  }

  public static AppModule_MainArgsFactory create(AppModule module) {
    return new AppModule_MainArgsFactory(module);
  }

  public static String[] mainArgs(AppModule instance) {
    return Preconditions.checkNotNull(instance.mainArgs(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
