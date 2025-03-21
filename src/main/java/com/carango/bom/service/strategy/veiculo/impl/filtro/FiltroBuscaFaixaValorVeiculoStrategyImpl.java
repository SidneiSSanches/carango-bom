package com.carango.bom.service.strategy.veiculo.impl.filtro;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import com.carango.bom.utils.ConverteEntityParaDtoUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Component
public class FiltroBuscaFaixaValorVeiculoStrategyImpl implements FiltroBuscaVeiculoStrategy {
  private VeiculoRepository veiculoRepository;

  @Override
  public Page<VeiculoDto> filtrar(Pageable pageable, FiltroBuscaVeiculoDto filtroBuscaVeiculoDto) {
    if (filtroBuscaVeiculoDto.valorMinimo() != null && filtroBuscaVeiculoDto.valorMaximo() != null && (
            filtroBuscaVeiculoDto.marcaId() == null)
    ) {
      return new PageImpl<>(
              listarPorFaixaValor(filtroBuscaVeiculoDto.valorMinimo(), filtroBuscaVeiculoDto.valorMaximo()),
              pageable,
              pageable.getPageSize()
      );
    }

    return null;
  }

  private List<VeiculoDto> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo) {
    return veiculoRepository.findAllByValorBetween(valorMinimo, valorMaximo).stream()
            .map(ConverteEntityParaDtoUtils::paraVeiculoDto)
            .toList();
  }
}