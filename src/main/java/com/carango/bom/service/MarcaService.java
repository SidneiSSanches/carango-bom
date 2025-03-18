package com.carango.bom.service;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarcaService {
  Page<MarcaEntity> listarTodas(Pageable paginacao);
  MarcaEntity buscarPorId(Long id);
  MarcaEntity salvar(MarcaEntity marca);
  void excluir(Long id);
}