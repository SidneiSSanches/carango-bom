package com.carango.bom.controller;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.impl.MarcaServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/marcas")
public class MarcaController {
    private MarcaServiceImpl service;

    @GetMapping
    public Page<MarcaEntity> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return service.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id) {
        MarcaEntity marcaEntity = service.buscarPorId(id);
        return ResponseEntity.ok(new MarcaDto(marcaEntity.getNome()));
    }

    @PostMapping
    public void criarMarca(@Valid @RequestBody MarcaDto marca) {
        service.criarMarca(marca);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/{marca_id}")
    public ResponseEntity atualizarMarca(
            @PathVariable(name = "marca_id") Long marcaId,
            @RequestBody MarcaDto marca) {
        return service.atualizarMarca(marcaId, marca);
    }
}