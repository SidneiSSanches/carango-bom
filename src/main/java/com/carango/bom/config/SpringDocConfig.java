package com.carango.bom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("Carango Bom")
	                    .version(appVersion)
	                    .description("API Rest da aplicação Carango Bom. Você pode saber mais sobre o Swagger em [http://swagger.io](http://swagger.io) ou em [irc.freenode.net, #swagger](http://swagger.io/irc/). Para este exemplo, você pode utilizar a API Token Login `special-key` para testes e autorização de filtros.")
	                    .termsOfService("http://swagger.io/terms/")
	                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	            .components(new Components()
	                    .addSecuritySchemes("bearer-key",
	                            new SecurityScheme().type(SecurityScheme.Type.HTTP)
	                                    .scheme("bearer")
	                                    .bearerFormat("JWT")))
	            .addSecurityItem(new SecurityRequirement().addList("bearer-key")); // Adicionando os requisitos de segurança
	}
}