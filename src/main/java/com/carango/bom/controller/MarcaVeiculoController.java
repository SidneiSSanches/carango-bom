package com.carango.bom.controller;

import com.carango.bom.model.MarcaVeiculo;
import com.carango.bom.service.MarcaVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class MarcaVeiculoController {

    @Autowired
    private MarcaVeiculoService service;

    @GetMapping
    public List<MarcaVeiculo> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<MarcaVeiculo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public MarcaVeiculo salvar(@RequestBody MarcaVeiculo marca) {
        return service.salvar(marca);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
