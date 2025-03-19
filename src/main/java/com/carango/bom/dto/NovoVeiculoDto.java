package com.carango.bom.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="Veiculo")
public record NovoVeiculoDto(

        Long idMarca,

        String modelo,

        Integer ano,

        BigDecimal valor
) {}