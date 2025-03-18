package com.carango.bom.service;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

import java.util.List;

public interface VeiculoService {
  void criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  List<VeiculoEntity> listarVeiculos();
}