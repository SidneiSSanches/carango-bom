package com.carango.bom.dto;

import java.math.BigDecimal;

public record FiltroBuscaVeiculoDto(
        Long marcaId,
        BigDecimal valorMinimo,
        BigDecimal valorMaximo
) {}