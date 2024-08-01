package com.gustavo.microservices.msclients.application;

import com.gustavo.microservices.msclients.domain.Client;
import com.gustavo.microservices.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRepository;

  @Transactional
  public Client save(Client client){
    return clientRepository.save(client);
  }

  public Optional<Client> getClientByCfp(String cpf){
    return clientRepository.findByCpf(cpf);
  }
}
