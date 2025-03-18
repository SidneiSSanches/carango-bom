package com.carango.bom.dto;

import java.math.BigDecimal;

public record NovoVeiculoDto(
        Long idMarca,
        String modelo,
        Integer ano,
        BigDecimal valor
) {}