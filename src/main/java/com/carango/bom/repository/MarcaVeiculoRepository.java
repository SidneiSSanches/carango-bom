package com.carango.bom.repository;


import com.carango.bom.model.MarcaVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaVeiculoRepository extends JpaRepository<MarcaVeiculo, Long> {
    Optional<MarcaVeiculo> findByNome(String nome);
}