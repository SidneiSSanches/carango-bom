package com.carango.bom.service.strategy.veiculo.impl.filtro;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import com.carango.bom.utils.ConverteEntityParaDtoUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FiltroBuscaTodosVeiculosStrategyImpl implements FiltroBuscaVeiculoStrategy {
  private VeiculoRepository veiculoRepository;

  @Override
  public Page<VeiculoDto> filtrar(Pageable pageable, FiltroBuscaVeiculoDto filtroBuscaVeiculoDto) {
    if (filtroBuscaVeiculoDto.marcaId() == null && (
            filtroBuscaVeiculoDto.valorMinimo() == null && filtroBuscaVeiculoDto.valorMaximo() == null)
    ){
      return veiculoRepository.findAll(pageable)
              .map(ConverteEntityParaDtoUtils::paraVeiculoDto);
    }

    return null;
  }
}