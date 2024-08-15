package com.gustavo.microservices.mscards.application;

import com.gustavo.microservices.mscards.domain.ClientCard;

import java.math.BigDecimal;

public record CardsByClientsResponse(String name, String flag, BigDecimal limitLiberal) {

  public static CardsByClientsResponse toDTO(ClientCard entity) {
    return new CardsByClientsResponse(entity.getCard().getName(), entity.getCard().getFlag()
            .toString(), entity.getCard().getCreditLimit());
  }
}
