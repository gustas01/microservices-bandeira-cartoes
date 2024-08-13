package com.gustavo.microservices.mscreditevaluator.application;

import com.gustavo.microservices.mscreditevaluator.application.exceptions.ClientNotFoundException;
import com.gustavo.microservices.mscreditevaluator.application.exceptions.ErrorComunicationMicroservicesException;
import com.gustavo.microservices.mscreditevaluator.application.exceptions.ErrorRequestCardException;
import com.gustavo.microservices.mscreditevaluator.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("creditevaluator")
@RequiredArgsConstructor
@Slf4j
public class CreditEvaluatorController {
  private final CreditEvaluatorService creditEvaluatorService;

  @GetMapping
  public String status() {
    log.info("Obtendo o status do ms de creditevaluator");
    return "ok de creditevaluator";
  }

  @GetMapping(value = "clientStatus", params = "cpf")
  public ResponseEntity getClientStatus(@RequestParam("cpf") String cpf) {
    try {
      ClientStatus clientStatus = creditEvaluatorService.getClientStatus(cpf);
      return ResponseEntity.ok(clientStatus);
    } catch (ClientNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (ErrorComunicationMicroservicesException e) {
      return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity MakeClientCheck(@RequestBody CheckData data){
    try {
      ClientCheckResponse clientCheckResponse = creditEvaluatorService.MakeClientCheck(data.getCpf(), data.getIncome());
      return ResponseEntity.ok(clientCheckResponse);
    } catch (ClientNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (ErrorComunicationMicroservicesException e) {
      return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
    }
  }

  @PostMapping("resquestcard")
  public ResponseEntity requestCard(@RequestBody CardRequestDTO data){
    try{
      RequestedCardProtocol requestedCardProtocol = creditEvaluatorService.requestEmissionCard(data);
      return ResponseEntity.ok(requestedCardProtocol);
    }catch (ErrorRequestCardException e){
      return ResponseEntity.internalServerError().body(e.getMessage());

    }
  }
}
