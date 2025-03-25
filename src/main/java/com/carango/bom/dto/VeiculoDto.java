package com.carango.bom.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record VeiculoDto(
        @Schema(description = "Campo id", example = "9999", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "Campo marca" , requiredMode = Schema.RequiredMode.REQUIRED)
        MarcaDto marca,
        @Schema(description = "Campo modelo", example = "Song-Pro",requiredMode = Schema.RequiredMode.REQUIRED)
        String modelo,
        @Schema(description="Campo ano", example = "2025",requiredMode = Schema.RequiredMode.REQUIRED)
        Integer ano,
        @Schema(description="Campo valor" ,example = "12000",requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal valor
) {}