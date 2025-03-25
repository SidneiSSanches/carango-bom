package com.carango.bom.repository.marca;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carango.bom.dto.DashboardMarcaProjecao;
import com.carango.bom.repository.marca.entity.MarcaEntity;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
    Optional<MarcaEntity> findByNome(String nome);
    
     @Query(value = """
             SELECT m as marca,COUNT(v) as quantidadeVeiculos, SUM(v.valor) as valorTotalVeiculos
                             FROM VeiculoEntity v
                             RIGHT JOIN MarcaEntity m
                             ON v.marca = m
                             GROUP BY m
             """)
        List<DashboardMarcaProjecao> getSumarioMarcas();
}