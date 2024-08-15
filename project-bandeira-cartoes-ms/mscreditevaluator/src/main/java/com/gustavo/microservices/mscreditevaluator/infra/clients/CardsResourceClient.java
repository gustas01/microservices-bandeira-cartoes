package com.gustavo.microservices.mscreditevaluator.infra.clients;

import com.gustavo.microservices.mscreditevaluator.domain.Card;
import com.gustavo.microservices.mscreditevaluator.domain.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

  @GetMapping(params = "cpf")
  ResponseEntity<List<ClientCard>> findCardByClient(@RequestParam("cpf") String cpf);

  @GetMapping(params = "income")
  ResponseEntity<List<Card>> getCardIncomeLessOrEqual(@RequestParam("income") Long income);
}
