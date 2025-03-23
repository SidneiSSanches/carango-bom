
package com.carango.bom.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

public class MarcaEntityTest {

    @Test
    public void testBuilder() {
        MarcaEntity marca = MarcaEntity.builder().id(1L).nome("Toyota").veiculoEntity(Collections.emptyList()).build();
        VeiculoEntity veiculo = VeiculoEntity.builder()
                .id(1L)
                .marca(marca)
                .modelo("Corolla")
                .ano(2025)
                .valor(new BigDecimal("20000"))
                .build();

        assertEquals(1L, veiculo.getId());
        assertEquals(marca, veiculo.getMarca());
        assertEquals("Corolla", veiculo.getModelo());
        assertEquals(2025, veiculo.getAno());
        assertEquals(new BigDecimal("20000"), veiculo.getValor());
    }
}
