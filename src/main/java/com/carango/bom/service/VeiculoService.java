package com.carango.bom.service;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VeiculoService {
  Page<VeiculoDto> listarVeiculos(Pageable paginacao, FiltroBuscaVeiculoDto filtroBuscaVeiculoDto);
  VeiculoDto criarVeiculo(NovoVeiculoDto novoVeiculoDto);
  void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto);
  void removerVeiculo(Long id);
}