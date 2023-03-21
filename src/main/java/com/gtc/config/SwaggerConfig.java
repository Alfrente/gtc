package com.gtc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Prueba tecnica gtc", version = "1.0",
        description = "Es una prueba tecnica de la empresa gtc"))
@Configuration
public class SwaggerConfig {
}
