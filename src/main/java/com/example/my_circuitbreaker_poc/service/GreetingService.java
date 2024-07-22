package com.example.my_circuitbreaker_poc.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
  private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

  @CircuitBreaker(name = "propertiesInstance")
  public String getResponseFrom(int number) {
    logger.info("getResponseFrom called with number: " + number);
    if (number > 1) {
      throw new NumberFormatException("que paso ?");
    }
    return "Operation Successful";
  }


  public String fallbackGreeting(int number, Exception e) {
    logger.info("Fallback called for number: " + number);
    return "Something happened, here is the exception e";
  }

}
