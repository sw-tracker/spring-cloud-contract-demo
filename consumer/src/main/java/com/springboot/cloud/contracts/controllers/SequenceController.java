package com.springboot.cloud.contracts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sequence")
public class SequenceController {

  private final RestTemplate restTemplate;

  @Autowired
  public SequenceController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/ok")
  public ResponseEntity<String> ok() {
    callSequence(1);
    callSequence(2);
    return ResponseEntity.status(200).body("ok");
  }

  // this would be invalid behaviour
  // only added to demonstrate that the order of the calls matter
  @GetMapping("/nok")
  public ResponseEntity<String> nok() {
    callSequence(2); // this will not work because WireMock expected call 1 first
    callSequence(1);
    return ResponseEntity.status(200).body("nok");
  }

  private ResponseEntity<String> callSequence(Integer number) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json");

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        "http://localhost:8090/sequence?order=" + number,
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        String.class);

    return responseEntity;
  }
}
