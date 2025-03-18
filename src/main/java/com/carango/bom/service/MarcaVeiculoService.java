package com.carango.bom.service;

import com.carango.bom.model.MarcaVeiculo;
import com.carango.bom.repository.MarcaVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaVeiculoService {

    @Autowired
    private MarcaVeiculoRepository repository;

    public Page<MarcaVeiculo> listarTodas(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Optional<MarcaVeiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public MarcaVeiculo salvar(MarcaVeiculo marca) {
        return repository.save(marca);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
