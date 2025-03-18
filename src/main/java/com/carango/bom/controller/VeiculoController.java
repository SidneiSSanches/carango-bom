package com.carango.bom.controller;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    return ResponseEntity.noContent()
            .build();
  }
}