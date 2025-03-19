package com.carango.bom.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@JsonRootName(value="Veiculo")
public record NovoVeiculoDto(

		@Schema(description="Campo de id marca" ,example= "99999",requiredMode = RequiredMode.REQUIRED)
        Long idMarca,
		@Schema(description="Campo de modelo" ,example= "Song-Pro",requiredMode = RequiredMode.REQUIRED)
        String modelo,
		@Schema(description="Campo de ano" ,example= "2025",requiredMode = RequiredMode.REQUIRED)
        Integer ano,
		@Schema(description="Campo de valor" ,example= "120000",requiredMode = RequiredMode.REQUIRED)

        BigDecimal valor
) {}