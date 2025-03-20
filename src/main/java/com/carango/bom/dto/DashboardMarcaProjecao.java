package com.carango.bom.dto;

import java.math.BigDecimal;

import com.carango.bom.repository.marca.entity.MarcaEntity;

public interface DashboardMarcaProjecao {

    MarcaEntity getMarca();
    Integer getQuantidadeVeiculos();
    BigDecimal getValorTotalVeiculos();

}
