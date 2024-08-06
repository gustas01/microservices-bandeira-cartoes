package com.gustavo.microservices.mscreditevaluator.domain;

import lombok.Data;

@Data
public class CheckData {
  private String cpf;
  private Long income;
}
