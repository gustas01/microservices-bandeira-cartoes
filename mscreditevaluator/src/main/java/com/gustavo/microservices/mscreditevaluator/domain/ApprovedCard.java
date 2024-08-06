package com.gustavo.microservices.mscreditevaluator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ApprovedCard {
  private String name;
  private String flag;
  private BigDecimal approvedLimit;
}
