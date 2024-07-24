package com.example.my_circuitbreaker_poc.controller;

import com.example.my_circuitbreaker_poc.exception.MyPOCBusinessException;
import com.example.my_circuitbreaker_poc.service.GreetingService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingController {

  private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final GreetingService greetingService;

  @GetMapping("/app/{number}")
  public ResponseEntity<String> greeting(@PathVariable int number) {
    return new ResponseEntity<>(greetingService.getResponseFrom(number), HttpStatus.OK);
  }

  @ExceptionHandler
  public ResponseEntity<String> handleCloseCircuitBreaker(CallNotPermittedException e){
    logger.error("CallNotPermittedException: " + e.getMessage());
    return new ResponseEntity<>("Circuit breaker is open", HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler
  public ResponseEntity<String> handlerControllerError(MyPOCBusinessException e){
    return new ResponseEntity<>(
      "Service failed, 1 < " + e.getNumber(),
      HttpStatus.BAD_REQUEST);
  }
}
