package com.gustavo.microservices.mscreditevaluator.application.exceptions;

import lombok.Getter;

@Getter
public class ErrorComunicationMicroservicesException extends Exception{

  private final Integer status;

  public ErrorComunicationMicroservicesException(String msg, Integer status) {
    super(msg);
    this.status = status;
  }
}
