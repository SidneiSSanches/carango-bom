package com.carango.bom.repository.veiculo;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
  List<VeiculoEntity> findByMarca(MarcaEntity marca);
  List<VeiculoEntity> findAllByValorBetween(BigDecimal valorMinimo, BigDecimal valorMaximo);
  List<VeiculoEntity> findAllByValorBetweenAndMarca(BigDecimal valorMinimo, BigDecimal valorMaximo, MarcaEntity marca);
}