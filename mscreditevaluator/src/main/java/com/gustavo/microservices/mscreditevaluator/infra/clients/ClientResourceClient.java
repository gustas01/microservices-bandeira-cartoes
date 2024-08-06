package com.gustavo.microservices.mscreditevaluator.infra.clients;

import com.gustavo.microservices.mscreditevaluator.domain.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clients")
public interface ClientResourceClient {

  @GetMapping(params = "cpf")
  ResponseEntity<ClientData> findOne (@RequestParam("cpf") String cpf);

}
