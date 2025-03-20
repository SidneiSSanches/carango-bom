package com.carango.bom.service.impl;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class VeiculoServiceImpl implements VeiculoService {
  private VeiculoRepository veiculoRepository;
  private MarcaService marcaService;

  @Transactional
  @Override
  public List<VeiculoEntity> listarVeiculos() {
    return veiculoRepository.findAll();
  }

  @Transactional
  @Override
  public List<VeiculoEntity> listarPorMarca(Long marcaId) {
    var marcaEntity = marcaService.buscarPorId(marcaId);

    return veiculoRepository.findByMarca(marcaEntity);
  }

  @Transactional
  @Override
  public List<VeiculoEntity> listarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo) {
    return veiculoRepository.findAllByValorBetween(valorMinimo, valorMaximo);
  }

  @Transactional
  @Override
  public void criarVeiculo(NovoVeiculoDto novoVeiculoDto) {
    var marcaVeiculoEntity = marcaService.buscarPorId(novoVeiculoDto.idMarca());

    var veiculoEntity = VeiculoEntity.builder()
            .marca(marcaVeiculoEntity)
            .modelo(novoVeiculoDto.modelo())
            .ano(novoVeiculoDto.ano())
            .valor(novoVeiculoDto.valor())
            .build();

    veiculoRepository.save(veiculoEntity);
  }

  @Transactional
  @Override
  public void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto) {
    var veiculoEntity = veiculoRepository.findById(id).orElseThrow();
    var marcaEntity = marcaService.buscarPorId(novoVeiculoDto.idMarca());

    veiculoEntity.setMarca(marcaEntity);
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
}