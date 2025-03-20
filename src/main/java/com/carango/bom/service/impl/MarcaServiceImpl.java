package com.carango.bom.service.impl;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.MarcaRepository;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.MarcaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MarcaServiceImpl implements MarcaService {
    private MarcaRepository marcaRepository;

    @Override
    public Page<MarcaDto> listarTodas(Pageable paginacao) {
        return marcaRepository.findAll(paginacao)
                .map(this::criarMarcaDto);
    }

    @Override
    public MarcaDto buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .map(this::criarMarcaDto)
                .orElseThrow(() -> new EntityNotFoundException("MarcaVeiculo com ID " + id + " não foi encontrada."));
    }

    @Transactional
    @Override
    public void criarMarca(MarcaDto marca) {
        var marcaEntity = MarcaEntity.builder()
                .nome(marca.nome())
                .build();

        marcaRepository.save(marcaEntity);
    }

    @Transactional
    @Override
    public void excluir(Long id) {
        var marcaEntity = marcaRepository.findById(id).orElseThrow();

        marcaRepository.delete(marcaEntity);
    }

    @Transactional
    @Override
    public void atualizarMarca(Long id, MarcaDto marcaDto) {
        var marcaEntity = marcaRepository.findById(id).orElseThrow();

        marcaEntity.setNome(marcaDto.nome());

        marcaRepository.save(marcaEntity);
    }

    private MarcaDto criarMarcaDto(MarcaEntity marcaEntity) {
        return new MarcaDto(
                marcaEntity.getId(),
                marcaEntity.getNome()
        );
    }
}