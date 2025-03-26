package com.carango.bom.controller.swagger;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Tag(name = "CRUD de veículos", description = "Gerencia as operações de veículos")
public interface VeiculoSwaggerController {
  @Operation(summary = "Lista Veiculos", description = "Funcionalidade de listagem dos veiculos cadastrados")
  Page<VeiculoDto> listarVeiculos(
          @Valid
          @ParameterObject @PageableDefault(size = 10,page=0,sort="marca,desc") Pageable paginacao,
          @RequestParam(name = "marca_id", required = false) Long marcaId,
          @RequestParam(name = "valor_minimo", required = false) BigDecimal valorMinimo,
          @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
  );

  @Operation(summary = "Cadastra Veiculo", description = "Funcionalidade de cadastro de um Veiculo",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> criarVeiculo(@Valid @RequestBody NovoVeiculoDto novoVeiculoDto);

  @Operation(summary = "Atualiza Veiculo", description = "Funcionalidade de Atualização de um Veiculo cadastrado",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> atualizarVeiculo(@Valid
                                                 @PathVariable(name = "veiculo_id") Long veiculoId,
                                                 @RequestBody NovoVeiculoDto novoVeiculoDto);

  @Operation(summary = "Exclui Veiculo", description = "Funcionalidade de exclusão de um Veiculo cadastrado",security = { @SecurityRequirement(name = "bearer-key") })
  ResponseEntity<Object> removerVeiculo(@Valid @PathVariable(name = "veiculo_id") Long veiculoId);
}