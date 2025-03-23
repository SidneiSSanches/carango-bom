package com.carango.bom.repository.marca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "marcas")
@JsonRootName(value="Marcas")
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="Campo id" ,example= "9999",requiredMode = RequiredMode.REQUIRED)
    private Long id;
    
    @Schema(description="Campo nome deve ter no mínimo 3 caracteres" ,example= "BYD",requiredMode = RequiredMode.REQUIRED )
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 ")
    @Column(nullable = false, unique = true)
    private String nome;
    
    @Schema(description="Campo lista de veiculos" ) 
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculoEntity;

	public @NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres") String nome) {
        this.nome = nome;
    }
}