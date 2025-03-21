package com.carango.bom.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carango.bom.dto.AuthenticationRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Autenticação", description = "Gerencia o login")
public interface AuthenticationSwaggerController {
	
	@PostMapping("/authenticate")
	@Operation(summary = "Login", description = "Funcionalidade de login")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest)
			throws Exception ;
	

}
