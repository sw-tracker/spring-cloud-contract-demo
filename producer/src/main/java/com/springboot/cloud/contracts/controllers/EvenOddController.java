package com.springboot.cloud.contracts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenOddController {

  @GetMapping("/validate/prime-number")
  public ResponseEntity<String> isNumberPrime(@RequestParam("number") Integer number) {
    return ResponseEntity.status(200).body(number % 2 == 0 ? "Even" : "Odd");
  }
}
