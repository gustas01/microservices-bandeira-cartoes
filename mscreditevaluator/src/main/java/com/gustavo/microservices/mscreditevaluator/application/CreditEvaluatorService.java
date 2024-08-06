package com.gustavo.microservices.mscreditevaluator.application;

import com.gustavo.microservices.mscreditevaluator.application.exceptions.ClientNotFoundException;
import com.gustavo.microservices.mscreditevaluator.application.exceptions.ErrorComunicationMicroservicesException;
import com.gustavo.microservices.mscreditevaluator.domain.*;
import com.gustavo.microservices.mscreditevaluator.infra.clients.CardsResourceClient;
import com.gustavo.microservices.mscreditevaluator.infra.clients.ClientResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {
  private final ClientResourceClient clientResourceClient;
  private final CardsResourceClient cardsResourceClient;

  public ClientStatus getClientStatus(String cpf) throws ClientNotFoundException, ErrorComunicationMicroservicesException {
    try {
      ResponseEntity<ClientData> clientData = clientResourceClient.findOne(cpf);
      ResponseEntity<List<ClientCard>> cardData = cardsResourceClient.findCardByClient(cpf);
      return ClientStatus.builder()
              .client(clientData.getBody())
              .cardsList(cardData.getBody())
              .build();
    } catch (FeignException.FeignClientException e) {
      if (e.status() == HttpStatus.NOT_FOUND.value())
        throw new ClientNotFoundException();
      throw new ErrorComunicationMicroservicesException(e.getMessage(), e.status());
    }
  }

  public ClientCheckResponse MakeClientCheck(String cpf, Long income) throws ClientNotFoundException, ErrorComunicationMicroservicesException {
    try {
      ResponseEntity<ClientData> clientData = clientResourceClient.findOne(cpf);
      ResponseEntity<List<Card>> cardsListData = cardsResourceClient.getCardIncomeLessOrEqual(income);

      List<ApprovedCard> approvedCardsList = cardsListData.getBody().stream().map(card -> {
        BigDecimal ageBD = BigDecimal.valueOf(clientData.getBody().getAge());
        BigDecimal approvedLimit = ageBD.divide(BigDecimal.valueOf(10)).multiply(card.getCreditLimit());

        ApprovedCard approvedCard = new ApprovedCard(card.getName(),
                card.getFlag(), approvedLimit);

        return approvedCard;
      }).toList();

      return new ClientCheckResponse(approvedCardsList);
    } catch (FeignException.FeignClientException e) {
      if (e.status() == HttpStatus.NOT_FOUND.value())
        throw new ClientNotFoundException();
      throw new ErrorComunicationMicroservicesException(e.getMessage(), e.status());
    }
  }
}
