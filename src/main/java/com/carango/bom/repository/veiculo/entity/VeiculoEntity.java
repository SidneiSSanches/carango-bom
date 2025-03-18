package com.carango.bom.repository.veiculo.entity;

import com.carango.bom.model.MarcaVeiculo;
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
public class VeiculoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name="marca_id")
  private MarcaVeiculo marcaVeiculo;
  @Column(nullable = false)
  private String modelo;
  @Column(nullable = false)
  private Integer ano;
  @Column(nullable = false)
  private BigDecimal valor;
}