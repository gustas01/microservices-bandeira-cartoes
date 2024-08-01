package com.gustavo.microservices.msclients.application;

import com.gustavo.microservices.msclients.domain.Client;
import lombok.Data;

@Data
public class CreateClientDto {
  private String cpf;
  private String name;
  private Integer age;

  public Client toModel(){
    return new Client(cpf, name, age);
  }
}
