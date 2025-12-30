package com.fit;

import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.core.optim.StaticOptimizations;
import java.lang.Override;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AOTApplicationContextConfigurer implements ApplicationContextConfigurer {
  static {
    enableEnvironmentCaching();
  }

  private static void enableEnvironmentCaching() {
    StaticOptimizations.cacheEnvironment();
  }

  private static List list0() {
    List result = new ArrayList<>(5);
    result.add("cached.environment");
    result.add("deduce.environment");
    result.add("graalvm.config");
    result.add("logback.xml.to.java");
    result.add("precompute.environment.properties");
    return result;
  }

  @Override
  public void configure(ApplicationContextBuilder builder) {
    builder.properties(new HashMap() {{
        put("micronaut.aot.enabled", true);
        put("micronaut.aot.runtime", "NATIVE");
        put("micronaut.aot.optimizations", list0());
        }});
  }
}
