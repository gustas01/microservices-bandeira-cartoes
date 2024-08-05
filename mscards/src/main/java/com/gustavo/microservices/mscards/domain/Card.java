package com.gustavo.microservices.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Flag flag;

  private BigDecimal income;

  private BigDecimal creditLimit;


  public Card(String name, Flag flag, BigDecimal income, BigDecimal creditLimit) {
    this.name = name;
    this.flag = flag;
    this.income = income;
    this.creditLimit = creditLimit;
  }
}
