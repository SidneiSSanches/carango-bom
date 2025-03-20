package com.carango.bom.controller;

import com.carango.bom.controller.handler.response.SuccessResponseHandler;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.impl.MarcaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SuccessResponseHandler successResponseHandler;

    @GetMapping
    @Operation(summary="Listagem Marca",tags="Listagem",description="Funcionalidade de listagem das marcas cadastradas")
    public Page<MarcaEntity> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return service.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    @Operation(summary="Busca Marca",tags="Busca",description="Funcionalidade de busca da marca cadastrada")
    public ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id) {
        MarcaEntity marcaEntity = service.buscarPorId(id);
        return ResponseEntity.ok(new MarcaDto(marcaEntity.getNome()));
    }

    @PostMapping
    @Operation(summary="Cadastro Marca",tags="Cadastro",description="Funcionalidade de cadastro de uma marca")
    public ResponseEntity<Object> criarMarca(@Valid @RequestBody MarcaDto marca) {
        service.criarMarca(marca);
        return successResponseHandler.created("/marcas", "Marca criada com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Exclusão Marca",tags="Exclusão",description="Funcionalidade de exclusão de uma marca cadastrada")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/{marca_id}")
    @Operation(summary="Atualização Marca",tags="Atualização",description="Funcionalidade de Atualização de uma marca cadastrada")
    public ResponseEntity atualizarMarca(
            @PathVariable(name = "marca_id") Long marcaId,
            @RequestBody MarcaDto marca) {
        return service.atualizarMarca(marcaId, marca);
    }
}