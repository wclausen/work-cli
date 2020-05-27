package com.wclausen.work.inject;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ConfigFileFactory implements Factory<File> {
  private final AppModule module;

  public AppModule_ConfigFileFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public File get() {
    return configFile(module);
  }

  public static AppModule_ConfigFileFactory create(AppModule module) {
    return new AppModule_ConfigFileFactory(module);
  }

  public static File configFile(AppModule instance) {
    return Preconditions.checkNotNull(instance.configFile(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
