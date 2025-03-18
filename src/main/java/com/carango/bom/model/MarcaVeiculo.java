package com.carango.bom.model;

import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "marcas")
public class MarcaVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToOne(mappedBy = "marcaVeiculo", cascade = CascadeType.ALL)
    private VeiculoEntity veiculoEntity;

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