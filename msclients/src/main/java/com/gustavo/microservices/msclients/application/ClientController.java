package com.gustavo.microservices.msclients.application;

import com.gustavo.microservices.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientService;

  @GetMapping
  public String status(){
    return "ok";
  }

  @PostMapping
  public ResponseEntity save(@RequestBody CreateClientDto createClientDto){
    Client client = createClientDto.toModel();
    clientService.save(client);
    URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}")
            .buildAndExpand(client.getCpf()).toUri();
    return ResponseEntity.created(headerLocation).build();

//    new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity findOne (@RequestParam("cpf") String cpf){
    Optional<Client> client = this.clientService.getClientByCfp(cpf);
    if (client.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(client.get());
  }
}
