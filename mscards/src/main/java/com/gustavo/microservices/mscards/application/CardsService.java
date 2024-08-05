package com.gustavo.microservices.mscards.application;

import com.gustavo.microservices.mscards.domain.Card;
import com.gustavo.microservices.mscards.infra.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardsService {
  private final CardsRepository cardsRepository;

  @Transactional
  public Card save(Card card){
    return cardsRepository.save(card);
  }

  public List<Card> getCardIncomeLessOrEqual(Long income){
    var incomeBigDecimal = BigDecimal.valueOf(income);
    return cardsRepository.findByIncomeLessThanEqual(incomeBigDecimal);
  }
}
