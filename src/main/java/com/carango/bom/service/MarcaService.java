package com.carango.bom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carango.bom.dto.DashboardMarcaProjecao;
import com.carango.bom.dto.MarcaDto;

public interface MarcaService {
  Page<MarcaDto> listarTodas(Pageable paginacao);
  MarcaDto buscarPorId(Long id);
  MarcaDto criarMarca(MarcaDto marca);
  void excluir(Long id);
  void atualizarMarca(Long id, MarcaDto marca);
  List<DashboardMarcaProjecao> getSumarioMarcas();
}