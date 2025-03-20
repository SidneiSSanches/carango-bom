package com.carango.bom.service;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface VeiculoService {
  Page<VeiculoDto> listarVeiculos(Pageable paginacao);
  List<VeiculoDto> listarPorMarca(Long marcaId);
  List<VeiculoDto> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo);
  VeiculoDto criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto);
  void removerVeiculo(Long id);
}