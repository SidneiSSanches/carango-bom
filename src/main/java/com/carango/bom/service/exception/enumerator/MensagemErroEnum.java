package com.carango.bom.service.exception.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemErroEnum {
  MARCA_NAO_ENCONTRADO("Não foi possível localizar a marca informada"),
  VEICULO_NAO_ENCONTRADO("Não foi possível localizar o veículo informado");

  private String texto;
}