package com.carango.bom.controller.swagger;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "CRUD de veículos", description = "Gerencia as operações de veículos")
public interface VeiculoSwaggerController {
  @Operation(summary = "Lista Veiculos", description = "Funcionalidade de listagem dos veiculos cadastrados")
  Page<VeiculoDto> listarVeiculos(@PageableDefault(size = 10) Pageable paginacao);

  @Operation(summary = "Busca Veiculos por Marca", description = "Funcionalidade de listagem dos veiculos cadastrados por marca")
  ResponseEntity<List<VeiculoDto>> listarPorMarca(@PathVariable(name = "marca_id") Long marcaId);

  @Operation(summary = "Lista Veiculos por faixa de preço", description = "Funcionalidade de listagem dos veiculos cadastrados por faixa de preço")
  ResponseEntity<List<VeiculoDto>> listarPorFaixa(@Valid
                                                         @RequestParam(name = "valor_minimo") BigDecimal valorMinimo,
                                                         @RequestParam(name = "valor_maximo") BigDecimal valorMaximo);

  @Operation(summary = "Cadastro Veiculo", description = "Funcionalidade de cadastro de um Veiculo")
  ResponseEntity<Object> criarVeiculo(@Valid @RequestBody NovoVeiculoDto novoVeiculoDto);

  @Operation(summary = "Atualização Veiculo", description = "Funcionalidade de Atualização de um Veiculo cadastrado")
  ResponseEntity<Object> atualizarVeiculo(@Valid
                                                 @PathVariable(name = "veiculo_id") Long veiculoId,
                                                 @RequestBody NovoVeiculoDto novoVeiculoDto);

  @Operation(summary = "Exclusão Veiculo", description = "Funcionalidade de exclusão de um Veiculo cadastrado")
  ResponseEntity<Object> removerVeiculo(@Valid @PathVariable(name = "veiculo_id") Long veiculoId);
}