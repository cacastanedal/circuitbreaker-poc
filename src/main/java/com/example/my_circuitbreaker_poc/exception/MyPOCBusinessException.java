package com.example.my_circuitbreaker_poc.exception;

public class MyPOCBusinessException extends RuntimeException{

  private int number;

  public MyPOCBusinessException(String errorMessage, int number){
    super(errorMessage);
    this.number = number;
  }

  public int getNumber() {
    return number;
  }
}
