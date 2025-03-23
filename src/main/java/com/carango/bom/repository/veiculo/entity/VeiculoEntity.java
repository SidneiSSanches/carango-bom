package com.carango.bom.repository.veiculo.entity;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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
@JsonRootName(value = "Veiculos")
public class VeiculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Campo id", example = "9999", requiredMode = RequiredMode.REQUIRED)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	@Schema(description = "Campo marca", requiredMode = RequiredMode.REQUIRED)
	private MarcaEntity marca;

	@Column(nullable = false)
	@Schema(description = "Campo modelo", example = "Song-Pro", requiredMode = RequiredMode.REQUIRED)
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(min = 2, max = 255, message = "O nome deve ter no mínimo 2 caracteres")
	private String modelo;

	@Column(nullable = false)
	@Schema(description = "Campo ano", example = "2025", requiredMode = RequiredMode.REQUIRED)
	@Positive(message = "O ano deve ser um número positivo.")
	private Integer ano;

	@Column(nullable = false)
	@Schema(description = "Campo valor", example = "12000", requiredMode = RequiredMode.REQUIRED)
	@Digits(integer = 8, fraction = 2, message = "O preço deve ser valor com duas decimais.")
	private BigDecimal valor;
}