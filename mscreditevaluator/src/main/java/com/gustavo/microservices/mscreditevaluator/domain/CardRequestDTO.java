package com.gustavo.microservices.mscreditevaluator.domain;

import java.math.BigDecimal;

public record CardRequestDTO(long cardId, String cpf, String address, BigDecimal limitAllowed) {
}
