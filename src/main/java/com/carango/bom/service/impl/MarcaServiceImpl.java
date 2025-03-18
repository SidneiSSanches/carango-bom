package com.carango.bom.service.impl;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.marca.MarcaVeiculoRepository;
import com.carango.bom.service.MarcaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MarcaServiceImpl implements MarcaService {
    private MarcaVeiculoRepository repository;

    @Override
    public Page<MarcaEntity> listarTodas(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @Override
    public MarcaEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("MarcaVeiculo com ID " + id + " não foi encontrada."));
    }

    @Override
    public MarcaEntity salvar(MarcaEntity marca) {
        return repository.save(marca);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}