package com.carango.bom.repository.veiculo.entity;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "veiculos")
@JsonRootName(value="Veiculos")
public class VeiculoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description="Campo id" ,requiredMode = RequiredMode.REQUIRED)
  private Long id;
  @ManyToOne
  @JoinColumn(name="marca_id")
  @Schema(description="Campo marca" ,requiredMode = RequiredMode.REQUIRED)
  private MarcaEntity marca;
  @Column(nullable = false)
  @Schema(description="Campo modelo" ,requiredMode = RequiredMode.REQUIRED)
  private String modelo;
  @Column(nullable = false)
  @Schema(description="Campo ano" ,requiredMode = RequiredMode.REQUIRED)
  private Integer ano;
  @Column(nullable = false)
  @Schema(description="Campo valor" ,requiredMode = RequiredMode.REQUIRED)
  private BigDecimal valor;
}