package com.carango.bom.controller;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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