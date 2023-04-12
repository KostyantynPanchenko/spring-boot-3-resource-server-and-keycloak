package com.example.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {

  @Bean
  @Order(1)
  SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();
  }

  @Bean
  @Order(2)
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(httpRequests -> httpRequests.anyRequest().authenticated())
        .formLogin().disable()
        .cors().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .build();
  }

}
