package com.carango.bom.controller;

import com.carango.bom.model.MarcaVeiculo;
import com.carango.bom.service.MarcaVeiculoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<MarcaVeiculo> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return service.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaVeiculo> buscarPorId(@PathVariable Long id) {
        MarcaVeiculo marca = service.buscarPorId(id);
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/{id}")
    public Optional<MarcaVeiculo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public MarcaVeiculo salvar(@Valid @RequestBody MarcaVeiculo marca) {
        return service.salvar(marca);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}