package com.carango.bom.utils;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConverteEntityParaDtoUtils {
  public static VeiculoDto paraVeiculoDto(VeiculoEntity veiculoEntity) {
    return new VeiculoDto(
            veiculoEntity.getId(),
            new MarcaDto(veiculoEntity.getMarca().getId(), veiculoEntity.getMarca().getNome()),
            veiculoEntity.getModelo(),
            veiculoEntity.getAno(),
            veiculoEntity.getValor()
    );
  }
}