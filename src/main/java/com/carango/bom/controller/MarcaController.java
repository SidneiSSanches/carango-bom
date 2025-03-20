package com.carango.bom.controller;

import com.carango.bom.controller.swagger.MarcaSwaggerController;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.service.impl.MarcaServiceImpl;

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
public class MarcaController implements MarcaSwaggerController {
    private MarcaServiceImpl marcaService;

    @GetMapping
    @Override
    public Page<MarcaDto> listarTodas(@PageableDefault(size = 10)  Pageable paginacao) {
        return marcaService.listarTodas(paginacao);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> criarMarca(@Valid @RequestBody MarcaDto marcaDto) {
        marcaService.criarMarca(marcaDto);

        return ResponseEntity.created( URI.create("/marcas"))
                .build();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Object> excluirMarca(@PathVariable Long id) {
        marcaService.excluir(id);

        return ResponseEntity.noContent()
                .build();
    }

    @PutMapping("/{marca_id}")
    @Override
    public ResponseEntity<Object> atualizarMarca(
            @PathVariable(name = "marca_id") Long marcaId,
            @RequestBody MarcaDto marcaDto) {
        marcaService.atualizarMarca(marcaId, marcaDto);

        return ResponseEntity.ok()
                .body(marcaDto);
    }
}