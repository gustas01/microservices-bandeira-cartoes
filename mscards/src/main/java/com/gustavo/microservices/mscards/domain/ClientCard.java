package com.gustavo.microservices.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
public class ClientCard {

  @Id
  @GeneratedValue
  private Long id;

  private String cpf;
  @ManyToOne
  private Card card;
  private BigDecimal creditLimit;

  public ClientCard(String cpf, Card card, BigDecimal creditLimit) {
    this.cpf = cpf;
    this.card = card;
    this.creditLimit = creditLimit;
  }
}
