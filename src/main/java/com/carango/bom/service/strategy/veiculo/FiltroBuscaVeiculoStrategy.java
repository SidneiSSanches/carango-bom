package com.carango.bom.service.strategy.veiculo;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiltroBuscaVeiculoStrategy {
  Page<VeiculoDto> filtrar(
          Pageable pageable,
          FiltroBuscaVeiculoDto filtroBuscaVeiculoDto
  );
}