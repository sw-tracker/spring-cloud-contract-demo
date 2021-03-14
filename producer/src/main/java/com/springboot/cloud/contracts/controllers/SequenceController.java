package com.springboot.cloud.contracts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sequence")
public class SequenceController {

  @GetMapping
  public ResponseEntity<String> sequence(@RequestParam("order") String order) {
    return ResponseEntity.status(200).body(order);
  }
}
