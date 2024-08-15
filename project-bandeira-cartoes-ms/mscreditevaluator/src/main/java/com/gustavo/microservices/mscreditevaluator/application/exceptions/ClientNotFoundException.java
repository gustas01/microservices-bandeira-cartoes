package com.gustavo.microservices.mscreditevaluator.application.exceptions;

public class ClientNotFoundException extends Exception{
  public ClientNotFoundException() {
    super("Cliente não encontrado!");
  }
}
