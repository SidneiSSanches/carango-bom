package com.carango.bom.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonRootName(value="Marca")
public record MarcaDto(
        @NotBlank(message = "O nome da marca não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
		@Schema(description="Campo de nome" ,example= "BYD",requiredMode = RequiredMode.REQUIRED)
   String nome
) {}