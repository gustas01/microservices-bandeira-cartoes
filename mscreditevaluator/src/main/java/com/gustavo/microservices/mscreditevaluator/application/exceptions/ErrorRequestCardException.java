package com.gustavo.microservices.mscreditevaluator.application.exceptions;

public class ErrorRequestCardException extends RuntimeException{
  public ErrorRequestCardException(String msg) {
    super(msg);
  }
}
