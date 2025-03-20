package com.carango.bom.repository.marca;


import com.carango.bom.repository.marca.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    Optional<MarcaEntity> findByNome(String nome);
}