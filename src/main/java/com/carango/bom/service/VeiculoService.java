package com.carango.bom.service;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VeiculoService {
  List<VeiculoEntity> listarVeiculos();
  List<VeiculoEntity> listarPorMarca(Long marcaId);
  List<VeiculoEntity> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo);
  void criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto);
  void removerVeiculo(Long id);
}