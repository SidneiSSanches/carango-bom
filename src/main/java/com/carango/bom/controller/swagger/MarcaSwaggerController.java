package com.carango.bom.controller.swagger;

import com.carango.bom.dto.MarcaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "CRUD de marcas", description = "Gerencia as operações de marca")
public interface MarcaSwaggerController {
  @Operation(summary = "Listagem Marca", description = "Funcionalidade de listagem das marcas cadastradas")
  Page<MarcaDto> listarTodas(@PageableDefault(size = 10) Pageable paginacao);

  ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id);

  @Operation(summary = "Cadastro Marca", description = "Funcionalidade de cadastro de uma marca")
  ResponseEntity<Object> criarMarca(@Valid @RequestBody MarcaDto marcaDto);

  @Operation(summary = "Exclusão Marca", description = "Funcionalidade de exclusão de uma marca cadastrada")
  ResponseEntity<Object> excluirMarca(@PathVariable Long id);

  @Operation(summary = "Atualização Marca", description = "Funcionalidade de Atualização de uma marca cadastrada")
  ResponseEntity<Object> atualizarMarca(
          @PathVariable(name = "marca_id") Long marcaId,
          @RequestBody MarcaDto marcaDto);
}