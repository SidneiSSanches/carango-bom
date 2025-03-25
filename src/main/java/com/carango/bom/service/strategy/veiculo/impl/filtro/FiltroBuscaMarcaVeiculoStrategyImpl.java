package com.carango.bom.service.strategy.veiculo.impl.filtro;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.utils.ConverteEntityParaDtoUtils;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.carango.bom.utils.ConverteDtoParaEntityUtils.paraMarcaEntity;

@AllArgsConstructor
@Component
public class FiltroBuscaMarcaVeiculoStrategyImpl implements FiltroBuscaVeiculoStrategy {
  private VeiculoRepository veiculoRepository;
  private MarcaService marcaService;

  @Override
  public Page<VeiculoDto> filtrar(Pageable pageable, FiltroBuscaVeiculoDto filtroBuscaVeiculoDto) {
    if (filtroBuscaVeiculoDto.marcaId() != null && (
            filtroBuscaVeiculoDto.valorMinimo() == null && filtroBuscaVeiculoDto.valorMaximo() == null
    )) {
      return new PageImpl<>(listarPorMarca(filtroBuscaVeiculoDto.marcaId()), pageable, pageable.getPageSize());
    }

    return null;
  }

  private List<VeiculoDto> listarPorMarca(Long marcaId) {
    var marcaDto = marcaService.buscarPorId(marcaId);

    return veiculoRepository.findByMarca(paraMarcaEntity(marcaDto)).stream()
            .map(ConverteEntityParaDtoUtils::paraVeiculoDto)
            .toList();
  }
}