package com.gustavo.microservices.msclients.application;

import com.gustavo.microservices.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientsController {
  private final ClientsService clientService;

  @GetMapping
  public String status(){
    log.info("Obtendo o status do ms de cliets");
    return "ok";
  }

  @PostMapping
  public ResponseEntity save(@RequestBody CreateClientDto createClientDto){
    Client client = createClientDto.toEntity();
    clientService.save(client);
    URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}")
            .buildAndExpand(client.getCpf()).toUri();
    return ResponseEntity.created(headerLocation).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity findOne (@RequestParam("cpf") String cpf){
    Optional<Client> client = this.clientService.getClientByCfp(cpf);
    if (client.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(client.get());
  }
}
