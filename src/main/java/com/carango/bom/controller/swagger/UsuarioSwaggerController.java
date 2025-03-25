package com.carango.bom.controller.swagger;

import com.carango.bom.dto.JwtDto;
import com.carango.bom.dto.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.carango.bom.dto.LoginDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Autenticação", description = "Gerencia o login")
public interface UsuarioSwaggerController {
	@Operation(summary = "Login", description = "Funcionalidade de login")
	ResponseEntity<JwtDto> logar(@Valid @RequestBody LoginDto authenticationRequest);
	@Operation(summary = "Login", description = "Funcionalidade de login")
	ResponseEntity<UsuarioDto> pegarDadosUsuario(@Valid @RequestHeader("Authorization") String token);
}