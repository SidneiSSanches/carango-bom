package com.carango.bom.utils;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConverteDtoParaEntityUtils {
  public static MarcaEntity paraMarcaEntity(MarcaDto marcaDto) {
    return MarcaEntity.builder()
            .id(marcaDto.id())
            .nome(marcaDto.nome())
            .build();
  }
}