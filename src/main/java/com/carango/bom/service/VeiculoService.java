package com.carango.bom.service;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

import java.util.List;

public interface VeiculoService {
  List<VeiculoEntity> listarVeiculos();
  void criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto);
  void removerVeiculo(Long id);
}