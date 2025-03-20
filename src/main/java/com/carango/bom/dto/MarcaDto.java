package com.carango.bom.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MarcaDto(
        @Schema(description="Campo id" ,example= "9999",requiredMode = RequiredMode.REQUIRED)
        Long id,
        @NotBlank(message = "O nome da marca não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O nome da marca deve ter entre 3 e 50 caracteres.")
        @Schema(description="Campo de nome" ,example= "BYD",requiredMode = RequiredMode.REQUIRED)
        String nome
) {}