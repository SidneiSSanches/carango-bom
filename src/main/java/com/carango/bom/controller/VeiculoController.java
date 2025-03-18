package com.carango.bom.controller;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
  private VeiculoService veiculoService;

  @GetMapping
  public ResponseEntity<List<VeiculoEntity>> listarVeiculos() {
    return ResponseEntity.ok()
            .body(veiculoService.listarVeiculos());
  }

  @GetMapping("/marcas/{marca_id}")
  public ResponseEntity<List<VeiculoEntity>> listarPorMarca(@PathVariable(name = "marca_id") Long marcaId) {
    return ResponseEntity.ok()
            .body(veiculoService.listarPorMarca(marcaId));
  }

  @GetMapping("/faixas")
  public ResponseEntity<List<VeiculoEntity>> listarPorFaixa(
          @RequestParam(name = "valor_minimo") BigDecimal valorMinimo,
          @RequestParam(name = "valor_maximo") BigDecimal valorMaximo) {
    return ResponseEntity.ok()
            .body(veiculoService.listarPorFaixaValor(valorMinimo, valorMaximo));
  }

  @PostMapping
  public ResponseEntity<Object> criarVeiculo(@RequestBody NovoVeiculoDto novoVeiculoDto) {
    veiculoService.criarVeiculo(novoVeiculoDto);

    return ResponseEntity.created(URI.create("/veiculos"))
            .build();
  }

  @PutMapping("/{veiculo_id}")
  public ResponseEntity<Object> atualizarVeiculo(
          @PathVariable(name = "veiculo_id") Long veiculoId,
          @RequestBody NovoVeiculoDto novoVeiculoDto) {
    veiculoService.atualizarVeiculo(veiculoId, novoVeiculoDto);

    return ResponseEntity.ok()
            .body("Veículo de id: " + veiculoId + " , atualizado com sucesso!");
  }

  @DeleteMapping("/{veiculo_id}")
  public ResponseEntity<Object> removerVeiculo(@PathVariable(name = "veiculo_id") Long veiculoId) {
    veiculoService.removerVeiculo(veiculoId);

    return ResponseEntity.ok()
            .body("Veículo de id: " + veiculoId + " , removido com sucesso!");
  }
}