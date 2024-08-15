package com.gustavo.microservices.mscards.infra.repository;

import com.gustavo.microservices.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardsRepository extends JpaRepository<Card, Long> {
  List<Card> findByIncomeLessThanEqual(BigDecimal incomeBigDecimal);
}
