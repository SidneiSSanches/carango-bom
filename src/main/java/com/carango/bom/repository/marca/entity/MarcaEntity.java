package com.carango.bom.repository.marca.entity;

import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "marcas")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculoEntity;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome (String nome){
        this.nome = nome;
    }
}