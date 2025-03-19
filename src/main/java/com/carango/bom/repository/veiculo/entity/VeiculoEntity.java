package com.carango.bom.repository.veiculo.entity;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
  private MarcaEntity marca;

  @Column(nullable = false)
  @NotBlank(message = "O nome não pode ser vazio")
  @Size(min = 2, max = 255, message = "O nome deve ter no mínimo 2 caracteres")
  private String modelo;

  @Column(nullable = false)
  @Positive(message = "O ano deve ser um número positivo.")
  private Integer ano;

  @Column(nullable = false)
  @Digits(integer = 8, fraction = 2, message = "O preço deve ser valor com duas decimais.")
  private BigDecimal valor;
}