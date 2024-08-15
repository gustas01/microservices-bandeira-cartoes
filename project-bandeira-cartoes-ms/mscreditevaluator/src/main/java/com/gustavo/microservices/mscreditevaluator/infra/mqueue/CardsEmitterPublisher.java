package com.gustavo.microservices.mscreditevaluator.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.microservices.mscreditevaluator.domain.CardRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardsEmitterPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Queue queueCardsEmitter;

  public void requestCard(CardRequestDTO data) throws JsonProcessingException {
    var json = convertIntoJson(data);
    rabbitTemplate.convertAndSend(queueCardsEmitter.getName(), json);
  }

  private String convertIntoJson(CardRequestDTO data) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    var json = mapper.writeValueAsString(data);
    return json;
  }
}
