package com.gustavo.microservices.mscreditevaluator.domain;

import java.util.List;

public record ClientCheckResponse(List<ApprovedCard> cards) {
}
