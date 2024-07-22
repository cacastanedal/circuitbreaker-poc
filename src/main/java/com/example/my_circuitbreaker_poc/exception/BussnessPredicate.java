package com.example.my_circuitbreaker_poc.exception;

import java.util.function.Predicate;

public class BussnessPredicate implements Predicate<Throwable> {

  @Override
  public boolean test(Throwable throwable) {
    System.out.println("On predicate execution");
    return true;
  }
}
