package com.example.keycloak.controller;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/some")
@SecurityScheme(
    name = "dashboard_api_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        clientCredentials = @OAuthFlow(
            tokenUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token",
            scopes = {
                @OAuthScope(name = "openid", description = "Default scope")
            })))
@Tag(name = "Protected resources", description = "Dummy API")
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
