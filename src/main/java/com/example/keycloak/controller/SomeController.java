package com.example.keycloak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/some")
class SomeController {

  @GetMapping(value = "/user")
  ResponseEntity<String> getUser() {
    return ResponseEntity.ok("Hello User");
  }

  @GetMapping(value = "/admin")
  ResponseEntity<String> getAdmin() {
    return ResponseEntity.ok("Hello Admin");
  }

}
