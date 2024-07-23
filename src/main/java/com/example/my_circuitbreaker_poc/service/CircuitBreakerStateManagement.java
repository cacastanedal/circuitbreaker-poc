package com.example.my_circuitbreaker_poc.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Profile("2020")
public class CircuitBreakerStateManagement {

  private final CircuitBreaker circuitBreaker;

  public CircuitBreakerStateManagement(CircuitBreakerRegistry circuitBreakerRegistry) {
    this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("propertiesInstance");
  }

  @Scheduled(cron = "0 * * * * *") // This runs every minute, adjust as necessary
  public void checkAndTransitionCircuitBreaker() {

    LocalTime now = LocalTime.now();
    LocalTime start = LocalTime.of(9, 0); // 9:00 AM
    LocalTime end = LocalTime.of(17, 0); // 5:00 PM

    if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
      if (now.isAfter(start) && now.isBefore(end)) {
        circuitBreaker.transitionToHalfOpenState();
      } else {
        circuitBreaker.transitionToOpenState();
      }
    }

  }
}
