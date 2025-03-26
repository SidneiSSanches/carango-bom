package com.carango.bom.controller;

import com.carango.bom.controller.swagger.VeiculoSwaggerController;
import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.service.VeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController implements VeiculoSwaggerController {
  private VeiculoService veiculoService;

  @GetMapping
  @Override
  public Page<VeiculoDto> listarVeiculos(
          @Valid
          @ParameterObject @PageableDefault(size = 10,page=0,sort="marca,desc") Pageable paginacao,
          @RequestParam(name = "marca_id", required = false) Long marcaId,
          @RequestParam(name = "valor_minimo", required = false) BigDecimal valorMinimo,
          @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
  ) {
    var filtroBuscaDto = new FiltroBuscaVeiculoDto(
            marcaId, valorMinimo, valorMaximo
    );

    return veiculoService.listarVeiculos(paginacao, filtroBuscaDto);
  }

  @PostMapping
  @Override
  public ResponseEntity<Object> criarVeiculo(@Valid @RequestBody NovoVeiculoDto novoVeiculoDto) {
    var veiculo = veiculoService.criarVeiculo(novoVeiculoDto);

    return ResponseEntity.created( URI.create("/veiculos/" + veiculo.id()))
            .build();
  }

  @PutMapping("/{veiculo_id}")
  @Override
  public ResponseEntity<Object> atualizarVeiculo(@Valid
          @PathVariable(name = "veiculo_id") Long veiculoId,
          @RequestBody NovoVeiculoDto novoVeiculoDto) {
    veiculoService.atualizarVeiculo(veiculoId, novoVeiculoDto);

    return ResponseEntity.ok()
            .body(novoVeiculoDto);
  }

  @DeleteMapping("/{veiculo_id}")
  @Override
  public ResponseEntity<Object> removerVeiculo(@Valid @PathVariable(name = "veiculo_id") Long veiculoId) {
    veiculoService.removerVeiculo(veiculoId);

    return ResponseEntity.noContent()
            .build();
  }
}