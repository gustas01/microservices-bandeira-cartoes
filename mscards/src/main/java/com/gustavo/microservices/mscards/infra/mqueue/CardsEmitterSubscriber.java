package com.gustavo.microservices.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.microservices.mscards.domain.Card;
import com.gustavo.microservices.mscards.domain.CardRequestDTO;
import com.gustavo.microservices.mscards.domain.ClientCard;
import com.gustavo.microservices.mscards.infra.repository.CardsRepository;
import com.gustavo.microservices.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardsEmitterSubscriber {

  private final CardsRepository cardsRepository;
  private final ClientCardRepository clientCardRepository;

  @RabbitListener(queues = "${mq.queues.cards-emitter}")
  public void receiveEmitterRequest(@Payload String payload) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      CardRequestDTO cardRequestDTO = mapper.readValue(payload, CardRequestDTO.class);
      Card card = cardsRepository.findById(cardRequestDTO.cardId()).orElseThrow();
      ClientCard clientCard = new ClientCard(cardRequestDTO.cpf(), card, cardRequestDTO.limitAllowed());

      clientCardRepository.save(clientCard);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
