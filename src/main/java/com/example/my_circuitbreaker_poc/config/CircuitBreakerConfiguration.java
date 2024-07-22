package com.example.my_circuitbreaker_poc.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfiguration {
  /*
  @Bean
  public CircuitBreakerConfigCustomizer changeCB(){
    return CircuitBreakerConfigCustomizer.of(
      "propertiesInstance",
      builder -> builder.recordException( throwable -> {
        System.out.println("Got throwable on recordException");
        return true;
      })
    );
  }
  */

}
