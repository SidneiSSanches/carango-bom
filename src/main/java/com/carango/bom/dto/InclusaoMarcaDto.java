package com.carango.bom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InclusaoMarcaDto(
		@JsonIgnore // Não exibe no Swagger nem na resposta JSON
        Long id,
        @Schema(description="Campo de nome" ,example= "BYD",requiredMode = RequiredMode.REQUIRED)
        @Size(min = 3, max = 50, message = "O nome da marca deve ter entre 3 e 50 caracteres.")
        @NotBlank(message = "O nome da marca não pode estar vazio.")
        String nome
) {
	
	
	public MarcaDto toMarcaDto(InclusaoMarcaDto inclusaoMarcaDto) {
	    return new MarcaDto(
	        inclusaoMarcaDto.id(), // O campo 'id' será transferido
	        inclusaoMarcaDto.nome() // O campo 'nome' será transferido
	    );
	}
	
	
	
}