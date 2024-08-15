package com.gustavo.microservices.mscreditevaluator.domain;

import lombok.Data;

@Data
public class ClientData {
  private Long id;
  private String cpf;
  private Integer age;
}
