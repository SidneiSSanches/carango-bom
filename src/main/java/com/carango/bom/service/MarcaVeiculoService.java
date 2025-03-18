package com.carango.bom.service;

import com.carango.bom.model.MarcaVeiculo;
import com.carango.bom.repository.MarcaVeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MarcaVeiculoService {
    private MarcaVeiculoRepository repository;

    public Page<MarcaVeiculo> listarTodas(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public MarcaVeiculo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("MarcaVeiculo com ID " + id + " não foi encontrada."));
    }

    public MarcaVeiculo salvar(MarcaVeiculo marca) {
        return repository.save(marca);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}