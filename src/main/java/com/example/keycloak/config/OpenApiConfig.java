package com.example.keycloak.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springframework.context.annotation.Bean;

/**
 * This class demonstrates how to enable auth for swagger documentation. If you wand to try it out -
 * comment documentation related annotations in controller class and add
 * {@link org.springframework.context.annotation.Configuration} annotation to this class.
 */
class OpenApiConfig {

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("spring_oauth", new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("Oauth2 Client Credentials flow")
                .flows(new OAuthFlows()
                    .clientCredentials(new OAuthFlow()
                        .tokenUrl(
                            "http://localhost:8088/realms/dashboard-api-realm/protocol/openid-connect/token")
                        .scopes(new Scopes()
                            .addString("openid", "default")
                        ))))
        )
        .security(List.of(new SecurityRequirement().addList("spring_oauth")))
        .info(new Info()
            .title("Sample application")
            .description(
                "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3")
            .termsOfService("TBD")
            .contact(new Contact().email("fake@email.net").name("Developer: FirstName LastName"))
            .license(new License().name("GNU"))
            .version("2.0")
        );
  }

}
