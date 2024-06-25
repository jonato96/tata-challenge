package com.tcs.challenge.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Api Tata Challenge",
                description = "Rest endpoints for bank transaction operations",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jonathan Sánchez",
                        url = "https://jonato96.github.io/tarjeta/"
                )
        )
)
public class SwaggerConfig {
}
