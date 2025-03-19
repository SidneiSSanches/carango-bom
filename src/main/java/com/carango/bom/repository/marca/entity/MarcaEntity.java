package com.carango.bom.repository.marca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "marcas")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres")
    @Column(nullable = false, unique = true)
    private String nome;
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculoEntity;

    public @NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome não pode ser vazio") @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres") String nome) {
        this.nome = nome;
    }
}