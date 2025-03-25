package com.carango.bom.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
public class DashboardMarcaDto {
    private final String nomeMarca;
    private final Integer quantidadeVeiculos;
    private final BigDecimal valor;

    public DashboardMarcaDto(DashboardMarcaProjecao sumarioMarcas) {
        this.nomeMarca = sumarioMarcas.getMarca().getNome();
        this.quantidadeVeiculos = sumarioMarcas.getQuantidadeVeiculos();
        this.valor = sumarioMarcas.getValorTotalVeiculos();
    }

    public static List<DashboardMarcaDto> converter(List<DashboardMarcaProjecao> sumarioMarcas) {
        return sumarioMarcas.stream().map(DashboardMarcaDto::new).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardMarcaDto that = (DashboardMarcaDto) o;
        return Objects.equals(nomeMarca, that.nomeMarca) && Objects.equals(quantidadeVeiculos, that.quantidadeVeiculos) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeMarca, quantidadeVeiculos, valor);
    }
}