package com.carango.bom.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;


@Entity
@Table(name = "marcas")
@Check(constraints = "LENGTH(nome) > 3")
public class MarcaVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

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
