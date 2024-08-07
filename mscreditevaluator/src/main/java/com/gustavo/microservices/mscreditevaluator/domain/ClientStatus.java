package com.gustavo.microservices.mscreditevaluator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientStatus {
  private ClientData client;
  private List<ClientCard> cardsList;
}
