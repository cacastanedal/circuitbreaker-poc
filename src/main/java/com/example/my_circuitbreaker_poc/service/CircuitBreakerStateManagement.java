package com.example.my_circuitbreaker_poc.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class CircuitBreakerStateManagement {

  private final CircuitBreaker circuitBreaker;

  private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerStateManagement.class);

  public CircuitBreakerStateManagement(CircuitBreakerRegistry circuitBreakerRegistry) {
    this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("propertiesInstance");
  }

  @Scheduled(cron = "0 * * * * *") // This runs every minute, adjust as necessary
  public void checkAndTransitionCircuitBreaker() {

    logger.info("On scheduled validation");

    LocalTime now = LocalTime.now();
    LocalTime start = LocalTime.of(9, 0); // 9:00 AM
    LocalTime end = LocalTime.of(17, 0); // 5:00 PM

    if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
      logger.info("Circuit breaker is open, validating time range");
      if (now.isAfter(start) && now.isBefore(end)) {
        logger.info("In range of the time range, try to start again");
        circuitBreaker.transitionToHalfOpenState();
      } else {
        logger.info("Outside of time range, keep it as it is");
      }
    }

  }
}
