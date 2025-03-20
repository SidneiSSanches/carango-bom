package com.carango.bom.service;

import com.carango.bom.dto.MarcaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarcaService {
  Page<MarcaDto> listarTodas(Pageable paginacao);
  MarcaDto buscarPorId(Long id);
  MarcaDto criarMarca(MarcaDto marca);
  void excluir(Long id);
  void atualizarMarca(Long id, MarcaDto marca);
}