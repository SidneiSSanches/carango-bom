package com.carango.bom.service.impl;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class VeiculoServiceImpl implements VeiculoService {
  private VeiculoRepository veiculoRepository;
  private MarcaService marcaService;

  @Override
  public Page<VeiculoDto> listarVeiculos(Pageable paginacao) {
    return veiculoRepository.findAll(paginacao)
            .map(this::criarVeiculoDto);
  }

  @Override
  public List<VeiculoDto> listarPorMarca(Long marcaId) {
    var marcaDto = marcaService.buscarPorId(marcaId);

    return veiculoRepository.findByMarca(criarMarcaEntity(marcaDto)).stream()
            .map(this::criarVeiculoDto)
            .toList();
  }

  @Override
  public List<VeiculoDto> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo) {
    return veiculoRepository.findAllByValorBetween(valorMinimo, valorMaximo).stream()
            .map(this::criarVeiculoDto)
            .toList();
  }

  @Transactional
  @Override
  public VeiculoDto criarVeiculo(NovoVeiculoDto novoVeiculoDto) {
    var marcaDto = marcaService.buscarPorId(novoVeiculoDto.marcaId());

    var veiculoEntity = VeiculoEntity.builder()
            .marca(criarMarcaEntity(marcaDto))
            .modelo(novoVeiculoDto.modelo())
            .ano(novoVeiculoDto.ano())
            .valor(novoVeiculoDto.valor())
            .build();

    return criarVeiculoDto(veiculoRepository.save(veiculoEntity));
  }

  @Transactional
  @Override
  public void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto) {
    var veiculoEntity = veiculoRepository.findById(id).orElseThrow();
    var marcaDto = marcaService.buscarPorId(novoVeiculoDto.marcaId());

    veiculoEntity.setMarca(criarMarcaEntity(marcaDto));
    veiculoEntity.setModelo(novoVeiculoDto.modelo());
    veiculoEntity.setAno(novoVeiculoDto.ano());
    veiculoEntity.setValor(novoVeiculoDto.valor());

    veiculoRepository.save(veiculoEntity);
  }

  @Transactional
  @Override
  public void removerVeiculo(Long id) {
    var veiculoEntity = veiculoRepository.findById(id).orElseThrow();

    veiculoRepository.delete(veiculoEntity);
  }

  private VeiculoDto criarVeiculoDto(VeiculoEntity veiculoEntity) {
    return new VeiculoDto(
            veiculoEntity.getId(),
            new MarcaDto(veiculoEntity.getMarca().getId(), veiculoEntity.getMarca().getNome()),
            veiculoEntity.getModelo(),
            veiculoEntity.getAno(),
            veiculoEntity.getValor()
    );
  }

  private MarcaEntity criarMarcaEntity(MarcaDto marcaDto) {
    return MarcaEntity.builder()
            .id(marcaDto.id())
            .nome(marcaDto.nome())
            .build();
  }
}