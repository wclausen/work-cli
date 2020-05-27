package com.wclausen.work.inject;

import com.wclausen.work.config.ConfigReader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_ConfigReaderFactory implements Factory<ConfigReader> {
  private final AppModule module;

  private final Provider<File> configFileProvider;

  public AppModule_ConfigReaderFactory(AppModule module, Provider<File> configFileProvider) {
    this.module = module;
    this.configFileProvider = configFileProvider;
  }

  @Override
  public ConfigReader get() {
    return configReader(module, configFileProvider.get());
  }

  public static AppModule_ConfigReaderFactory create(AppModule module,
      Provider<File> configFileProvider) {
    return new AppModule_ConfigReaderFactory(module, configFileProvider);
  }

  public static ConfigReader configReader(AppModule instance, File configFile) {
    return Preconditions.checkNotNull(instance.configReader(configFile), "Cannot return null from a non-@Nullable @Provides method");
  }
}
