package com.dossancto.todo.dossancto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Dossancto API")
						.version("1.0")
						.description("Todo application API")
						.contact(new Contact()
								.name("Support")
								.email("support@dossancto.com")));
	}
}
