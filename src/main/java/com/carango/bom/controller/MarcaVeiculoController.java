package com.carango.bom.controller;

import com.carango.bom.repository.marca.entity.MarcaEntity;

import com.carango.bom.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/marcas")
public class MarcaVeiculoController {
    private MarcaService service;

    @GetMapping
    public Page<MarcaEntity> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return service.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaEntity> buscarPorId(@PathVariable Long id) {
        MarcaEntity marca = service.buscarPorId(id);
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public MarcaEntity salvar(@Valid @RequestBody MarcaEntity marca) {
        return service.salvar(marca);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}