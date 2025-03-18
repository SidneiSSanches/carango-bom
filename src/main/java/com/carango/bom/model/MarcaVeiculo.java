package com.carango.bom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "marcas")
public class MarcaVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @OneToMany(mappedBy = "marcaVeiculo", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculoEntity;

    public MarcaVeiculo() {}

    public MarcaVeiculo(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}