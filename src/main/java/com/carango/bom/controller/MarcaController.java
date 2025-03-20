package com.carango.bom.controller;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.impl.MarcaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/marcas")
public class MarcaController {
    private MarcaServiceImpl marcaService;

    @GetMapping
    @Operation(summary="Listagem Marca",tags="Listagem",description="Funcionalidade de listagem das marcas cadastradas")
    public Page<MarcaEntity> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return marcaService.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    @Operation(summary="Busca Marca",tags="Busca",description="Funcionalidade de busca da marca cadastrada")
    public ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id) {
        MarcaEntity marcaEntity = marcaService.buscarPorId(id);
        return ResponseEntity.ok(new MarcaDto(marcaEntity.getId(), marcaEntity.getNome()));
    }

    @PostMapping
    @Operation(summary="Cadastro Marca",tags="Cadastro",description="Funcionalidade de cadastro de uma marca")
    public ResponseEntity<Object> criarMarca(@Valid @RequestBody MarcaDto marcaDto) {
        marcaService.criarMarca(marcaDto);

        return ResponseEntity.created( URI.create("/marcas"))
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Exclusão Marca",tags="Exclusão",description="Funcionalidade de exclusão de uma marca cadastrada")
    public ResponseEntity<Object> excluirMarca(@PathVariable Long id) {
        marcaService.excluir(id);

       return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{marca_id}")
    @Operation(summary="Atualização Marca",tags="Atualização",description="Funcionalidade de Atualização de uma marca cadastrada")
    public ResponseEntity<Object> atualizarMarca(
            @PathVariable(name = "marca_id") Long marcaId,
            @RequestBody MarcaDto marcaDto) {
        marcaService.atualizarMarca(marcaId, marcaDto);

        return ResponseEntity.ok()
                .body(marcaDto);
    }
}