package com.gustavo.microservices.mscreditevaluator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
  @Value("${mq.queues.cards-emitter}")
  private String cardsEmitterQueue;

  @Bean
  public Queue cardsEmitterQueue(){
    return new Queue(cardsEmitterQueue, true);
  }
}
