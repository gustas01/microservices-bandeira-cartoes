package com.gustavo.microservices.msclients.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue
  private Long id;

  private String cpf;
  private String name;
  private Integer age;

  public Client(String cpf, String name, Integer age) {
    this.cpf = cpf;
    this.name = name;
    this.age = age;
  }
}
