package com.gustavo.microservices.mscards.application;

import com.gustavo.microservices.mscards.domain.Card;
import com.gustavo.microservices.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
@Slf4j
public class CardsController {
  private final CardsService cardsService;
  private final ClientCardService clientCardService;

  @GetMapping
  public String status(){
    log.info("Obtendo o status do ms de cards");
    return "ok";
  }

  @PostMapping
  public ResponseEntity registerCard(@RequestBody CreateCardDto createCardDto){
    Card card = createCardDto.toEntity();
    cardsService.save(card);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "income")
  public ResponseEntity<List<Card>> getCardIncomeLessOrEqual(@RequestParam("income") Long income){
    List<Card> cards = cardsService.getCardIncomeLessOrEqual(income);
    return ResponseEntity.ok(cards);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CardsByClientsResponse>> findCardByClient(@RequestParam("cpf") String cpf){
    List<ClientCard> clientCards = this.clientCardService.findCardsByCpf(cpf);
    List<CardsByClientsResponse> cardsByClientsResponseList = clientCards.stream().map(CardsByClientsResponse::toDTO).toList();
    return ResponseEntity.ok(cardsByClientsResponseList);
  }
}
