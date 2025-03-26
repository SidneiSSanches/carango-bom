package com.carango.bom.controller.swagger;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.carango.bom.dto.DashboardMarcaDto;
import com.carango.bom.dto.InclusaoMarcaDto;
import com.carango.bom.dto.MarcaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "CRUD de marcas", description = "Gerencia as operações de marca")
public interface MarcaSwaggerController {
  @Operation(summary = "Lista Marca", description = "Funcionalidade de listagem das marcas cadastradas",security = { @SecurityRequirement(name = "bearer-key") })
  Page<MarcaDto> listarTodas(@ParameterObject @PageableDefault(size = 10,page=0,sort="nome,desc")  Pageable paginacao);

  @Operation(summary = "Busca Marca", description = "Funcionalidade de busca por id",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<MarcaDto> buscarPorId(@PathVariable Long id);

  @Operation(summary = "Cadastra Marca", description = "Funcionalidade de cadastro de uma marca",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> criarMarca(@Valid @RequestBody InclusaoMarcaDto inclusaoMarcaDto);

  @Operation(summary = "Exclui Marca", description = "Funcionalidade de exclusão de uma marca cadastrada",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> excluirMarca(@PathVariable Long id);

  @Operation(summary = "Atualiza Marca", description = "Funcionalidade de Atualização de uma marca cadastrada",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> atualizarMarca( @PathVariable(name = "marca_id") Long marcaId,@RequestBody InclusaoMarcaDto inclusaoMarcaDto);
  
  @Operation(summary = "Dashboard", description = "Funcionalidade de dashboard",security = { @SecurityRequirement(name = "bearer-key") })
  List<DashboardMarcaDto> consultaDashboardMarcas();
}