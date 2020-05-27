package com.wclausen.work.config;

import dagger.internal.Factory;
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
public final class RealConfigReader_Factory implements Factory<RealConfigReader> {
  private final Provider<File> configFileProvider;

  public RealConfigReader_Factory(Provider<File> configFileProvider) {
    this.configFileProvider = configFileProvider;
  }

  @Override
  public RealConfigReader get() {
    return newInstance(configFileProvider.get());
  }

  public static RealConfigReader_Factory create(Provider<File> configFileProvider) {
    return new RealConfigReader_Factory(configFileProvider);
  }

  public static RealConfigReader newInstance(File configFile) {
    return new RealConfigReader(configFile);
  }
}
