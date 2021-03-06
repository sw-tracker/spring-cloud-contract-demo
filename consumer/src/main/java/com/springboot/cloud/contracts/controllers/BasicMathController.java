package com.springboot.cloud.contracts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/calculate")
public class BasicMathController {

  private final RestTemplate restTemplate;

  @Autowired
  public BasicMathController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping
  public String checkOddAndEven(@RequestParam("number") Integer number) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json");

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        "http://localhost:8090/validate/prime-number?number=" + number,
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        String.class);

    return responseEntity.getBody();
  }
}