package com.gustavo.microservices.mscards.application;

import com.gustavo.microservices.mscards.domain.ClientCard;
import com.gustavo.microservices.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
  private final ClientCardRepository clientCardRepository;

  public List<ClientCard> findCardsByCpf(String cpf){
    return clientCardRepository.findByCpf(cpf);
  }
}
