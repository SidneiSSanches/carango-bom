package com.carango.bom.service;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MarcaService {
  Page<MarcaEntity> listarTodas(Pageable paginacao);
  MarcaEntity buscarPorId(Long id);
  void criarMarca(MarcaDto marca);
  void excluir(Long id);
  ResponseEntity atualizarMarca(Long id, MarcaDto marca);
}