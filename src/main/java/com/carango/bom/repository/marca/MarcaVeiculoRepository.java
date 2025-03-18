package com.carango.bom.repository.marca;


import com.carango.bom.repository.marca.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaVeiculoRepository extends JpaRepository<MarcaEntity, Long> {
    Optional<MarcaEntity> findByNome(String nome);
}