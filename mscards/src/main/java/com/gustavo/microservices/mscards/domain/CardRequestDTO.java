package com.gustavo.microservices.mscards.domain;

import java.math.BigDecimal;

public record CardRequestDTO(long cardId, String cpf, String address, BigDecimal limitAllowed) {
}
