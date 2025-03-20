package com.carango.bom.springdoc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {
	
  
    
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
        		.info(new Info()
                .title("Caranga-BOM API")
                .version(appVersion)
                .description("API Rest da aplicacao Caranga-bom. Voce pode saber mais do Swagger em [http://swagger.io](http://swagger.io) ou em [irc.freenode.net, #swagger](http://swagger.io/irc/). Para este exemplo voce pode utilizar a api Token Login `special-key` para testes e autorizacao de filtros.")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                ;
    }



}
