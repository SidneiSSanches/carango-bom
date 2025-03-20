package com.carango.bom.service;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface VeiculoService {
  Page<VeiculoEntity> listarVeiculos(Pageable paginacao);
  List<VeiculoEntity> listarPorMarca(Long marcaId);
  List<VeiculoEntity> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo);
  void criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto);
  void removerVeiculo(Long id);
}