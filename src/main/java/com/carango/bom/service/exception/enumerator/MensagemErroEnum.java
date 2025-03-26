package com.carango.bom.service.exception.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemErroEnum {
  MARCA_NAO_ENCONTRADO("Não foi possível localizar a marca informada"),
  VEICULO_NAO_ENCONTRADO("Não foi possível localizar o veículo informado"),
  DASHBOARD_NAO_ENCONTRADO("Não foi possível localizar as informações do dashboard");

  private String texto;
}