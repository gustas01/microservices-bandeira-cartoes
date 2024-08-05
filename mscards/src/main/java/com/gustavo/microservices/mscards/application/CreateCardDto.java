package com.gustavo.microservices.mscards.application;

import com.gustavo.microservices.mscards.domain.Card;
import com.gustavo.microservices.mscards.domain.Flag;
import lombok.Data;

import java.math.BigDecimal;

public record CreateCardDto (String name, Flag flag, BigDecimal income, BigDecimal creditLimit){


  public Card toEntity(){
    return new Card(name, flag, income, creditLimit);
  }
}
