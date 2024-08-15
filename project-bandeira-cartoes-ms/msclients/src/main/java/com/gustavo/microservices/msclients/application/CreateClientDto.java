package com.gustavo.microservices.msclients.application;

import com.gustavo.microservices.msclients.domain.Client;

public record CreateClientDto(String cpf, String name, Integer age) {


  public Client toEntity(){
    return new Client(cpf, name, age);
  }
}
