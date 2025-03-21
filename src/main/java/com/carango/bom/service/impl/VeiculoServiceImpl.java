package com.carango.bom.service.impl;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.VeiculoService;
import com.carango.bom.service.exception.DadoNaoEncontrado;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.carango.bom.utils.ConverteDtoParaEntityUtils.paraMarcaEntity;
import static com.carango.bom.utils.ConverteEntityParaDtoUtils.paraVeiculoDto;
import static com.carango.bom.service.exception.enumerator.MensagemErroEnum.VEICULO_NAO_ENCONTRADO;

@AllArgsConstructor
@Service
public class VeiculoServiceImpl implements VeiculoService {
  private VeiculoRepository veiculoRepository;
  private MarcaService marcaService;
  private List<FiltroBuscaVeiculoStrategy> filtroBuscaVeiculoStrategyList;

  @Override
  public Page<VeiculoDto> listarVeiculos(Pageable paginacao, FiltroBuscaVeiculoDto filtroBuscaVeiculoDto) {
   var page = new AtomicReference<Page<VeiculoDto>>();

    filtroBuscaVeiculoStrategyList
            .forEach(strategy -> {
              if (page.get() == null) {
                page.set(strategy.filtrar(paginacao, filtroBuscaVeiculoDto));
              }
            });

    return page.get();
  }

  @Transactional
  @Override
  public VeiculoDto criarVeiculo(NovoVeiculoDto novoVeiculoDto) {
    var marcaDto = marcaService.buscarPorId(novoVeiculoDto.marcaId());

    var veiculoEntity = VeiculoEntity.builder()
            .marca(paraMarcaEntity(marcaDto))
            .modelo(novoVeiculoDto.modelo())
            .ano(novoVeiculoDto.ano())
            .valor(novoVeiculoDto.valor())
            .build();

    return paraVeiculoDto(veiculoRepository.save(veiculoEntity));
  }

  @Transactional
  @Override
  public void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto) {
    var veiculoEntity = veiculoRepository.findById(id)
            .orElseThrow(() -> new DadoNaoEncontrado(VEICULO_NAO_ENCONTRADO.getTexto()));

    var marcaDto = marcaService.buscarPorId(novoVeiculoDto.marcaId());

    veiculoEntity.setMarca(paraMarcaEntity(marcaDto));
    veiculoEntity.setModelo(novoVeiculoDto.modelo());
    veiculoEntity.setAno(novoVeiculoDto.ano());
    veiculoEntity.setValor(novoVeiculoDto.valor());

    veiculoRepository.save(veiculoEntity);
  }

  @Transactional
  @Override
  public void removerVeiculo(Long id) {
    var veiculoEntity = veiculoRepository.findById(id)
            .orElseThrow(() -> new DadoNaoEncontrado(VEICULO_NAO_ENCONTRADO.getTexto()));

    veiculoRepository.delete(veiculoEntity);
  }
}