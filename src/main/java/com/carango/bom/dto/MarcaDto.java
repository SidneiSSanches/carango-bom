package com.carango.bom.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@JsonRootName(value="Marca")
public record MarcaDto(
		@Schema(description="Campo de senha" ,requiredMode = RequiredMode.REQUIRED)
   String nome
) {}