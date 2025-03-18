package com.carango.bom.service.impl;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.MarcaVeiculoService;
import com.carango.bom.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VeiculoServiceImpl implements VeiculoService {
  private VeiculoRepository veiculoRepository;
  private MarcaVeiculoService marcaVeiculoService;

  @Override
  public List<VeiculoEntity> listarVeiculos() {
    return veiculoRepository.findAll();
  }

  @Override
  public void criarVeiculo(NovoVeiculoDto novoVeiculoDto) {
    var marcaVeiculoEntity = marcaVeiculoService.buscarPorId(novoVeiculoDto.idMarca())
            .orElseThrow();

    var veiculoEntity = VeiculoEntity.builder()
            .marcaVeiculo(marcaVeiculoEntity)
            .modelo(novoVeiculoDto.modelo())
            .ano(novoVeiculoDto.ano())
            .valor(novoVeiculoDto.valor())
            .build();

    veiculoRepository.save(veiculoEntity);
  }

  @Override
  public void atualizarVeiculo(Long id, NovoVeiculoDto novoVeiculoDto) {
    var veiculoEntity = veiculoRepository.findById(id).orElseThrow();
    var marcaEntity = marcaVeiculoService.buscarPorId(novoVeiculoDto.idMarca()).orElseThrow();

    veiculoEntity.setMarcaVeiculo(marcaEntity);
    veiculoEntity.setModelo(novoVeiculoDto.modelo());
    veiculoEntity.setAno(novoVeiculoDto.ano());
    veiculoEntity.setValor(novoVeiculoDto.valor());

    veiculoRepository.save(veiculoEntity);
  }

  @Override
  public void removerVeiculo(Long id) {
    var veiculoEntity = veiculoRepository.findById(id).orElseThrow();

    veiculoRepository.delete(veiculoEntity);
  }
}