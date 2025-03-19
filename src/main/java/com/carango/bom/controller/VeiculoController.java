package com.carango.bom.controller;

import com.carango.bom.controller.handler.response.SuccessResponseHandler;
import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
  private VeiculoService veiculoService;

  @Autowired
  private SuccessResponseHandler successResponseHandler;

  @GetMapping
  @Operation(summary="Lista Veiculos",tags="Listagem",description="Funcionalidade de listagem dos veiculos cadastrados")
  public Page<VeiculoEntity> listarVeiculos(@PageableDefault(size = 10) Pageable paginacao) {
    return veiculoService.listarVeiculos(paginacao);
  }

  @GetMapping("/marcas/{marca_id}")
  @Operation(summary="Busca Veiculos por Marca",tags="Busca",description="Funcionalidade de listagem dos veiculos cadastrados por marca")
  public ResponseEntity<List<VeiculoEntity>> listarPorMarca(@PathVariable(name = "marca_id") Long marcaId) {
    return ResponseEntity.ok()
            .body(veiculoService.listarPorMarca(marcaId));
  }

  @GetMapping("/faixas")
  @Operation(summary="Lista Veiculos por faixa de preço",tags="Listagem",description="Funcionalidade de listagem dos veiculos cadastrados por faixa de preço")
  public ResponseEntity<List<VeiculoEntity>> listarPorFaixa(@Valid
          @RequestParam(name = "valor_minimo") BigDecimal valorMinimo,
          @RequestParam(name = "valor_maximo") BigDecimal valorMaximo) {
    return ResponseEntity.ok()
            .body(veiculoService.listarPorFaixaValor(valorMinimo, valorMaximo));
  }

  @PostMapping
  @Operation(summary="Cadastro Veiculo",tags="Cadastro",description="Funcionalidade de cadastro de um Veiculo")
  public ResponseEntity<Object> criarVeiculo(@Valid @RequestBody NovoVeiculoDto novoVeiculoDto) {
    veiculoService.criarVeiculo(novoVeiculoDto);
    return successResponseHandler.created("/veiculos", "Veículo criada com sucesso!");
  }

  @PutMapping("/{veiculo_id}")
  @Operation(summary="Atualização Veiculo",tags="Atualização",description="Funcionalidade de Atualização de um Veiculo cadastrado")
  public ResponseEntity<Object> atualizarVeiculo(@Valid
          @PathVariable(name = "veiculo_id") Long veiculoId,
          @RequestBody NovoVeiculoDto novoVeiculoDto) {
    veiculoService.atualizarVeiculo(veiculoId, novoVeiculoDto);

    return ResponseEntity.ok()
            .body(novoVeiculoDto);
  }

  @DeleteMapping("/{veiculo_id}")
  @Operation(summary="Exclusão Veiculo",tags="Exclusão",description="Funcionalidade de exclusão de um Veiculo cadastrado")
  public ResponseEntity<Object> removerVeiculo(@Valid @PathVariable(name = "veiculo_id") Long veiculoId) {
    veiculoService.removerVeiculo(veiculoId);

    return ResponseEntity.noContent()
            .build();
  }
}