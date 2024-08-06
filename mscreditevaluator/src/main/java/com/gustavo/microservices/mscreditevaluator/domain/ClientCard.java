package com.gustavo.microservices.mscreditevaluator.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {
  private String name;
  private String flag;
  private BigDecimal creditLimit;
}
