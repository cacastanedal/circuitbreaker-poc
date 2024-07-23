package com.example.my_circuitbreaker_poc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class BussnessPredicate implements Predicate<Throwable> {

  private static final Logger logger = LoggerFactory.getLogger(BussnessPredicate.class);

  @Override
  public boolean test(Throwable throwable) {
    logger.trace("Validating circuit breaker error = " + throwable.getLocalizedMessage());
    return true;
  }
}
