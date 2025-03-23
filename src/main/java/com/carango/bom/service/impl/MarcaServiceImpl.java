package com.carango.bom.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.MarcaVeiculoRepository;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.MarcaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

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

    @Transactional
    @Override
    public void criarMarca(MarcaDto marca) {
        var marcaEntity = MarcaEntity.builder()
                .nome(marca.nome())
                .build();
        repository.save(marcaEntity);
    }

    @Transactional
    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public ResponseEntity atualizarMarca(Long id, MarcaDto marca) {
        var marcaEntity = repository.findById(id);

        if (marcaEntity.isPresent()) {
            var marcaEntityAtualizada = MarcaEntity.builder()
                    .id(id)
                    .nome(marca.nome())
                    .build();

            repository.save(marcaEntityAtualizada);
            return ResponseEntity.ok().body(marca);
        } else{
            return ResponseEntity.noContent().build();
        }
    }
}