package com.carango.bom.service.impl;

import static com.carango.bom.service.exception.enumerator.MensagemErroEnum.MARCA_NAO_ENCONTRADO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carango.bom.dto.DashboardMarcaProjecao;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.MarcaRepository;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.exception.DadoNaoEncontradoException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MarcaServiceImpl implements MarcaService {
    private MarcaRepository marcaRepository;

    @Override
    public Page<MarcaDto> listarTodas(Pageable paginacao) {
        return marcaRepository.findAll(paginacao)
                .map(this::criarMarcaDto);
    }

    @Override
    public MarcaDto buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .map(this::criarMarcaDto)
                .orElseThrow(() -> new DadoNaoEncontradoException(MARCA_NAO_ENCONTRADO.getTexto()));
    }

    @Transactional
    @Override
    public MarcaDto criarMarca(MarcaDto marca) {
        var marcaEntity = MarcaEntity.builder()
                .nome(marca.nome())
                .build();

        return criarMarcaDto(marcaRepository.save(marcaEntity));
    }

    @Transactional
    @Override
    public void excluir(Long id) {
        var marcaEntity = marcaRepository.findById(id)
                .orElseThrow(() -> new DadoNaoEncontradoException(MARCA_NAO_ENCONTRADO.getTexto()));

        marcaRepository.delete(marcaEntity);
    }

    @Transactional
    @Override
    public void atualizarMarca(Long id, MarcaDto marcaDto) {
        var marcaEntity = marcaRepository.findById(id)
                .orElseThrow(() -> new DadoNaoEncontradoException("Não foi possível localizar a marca informada"));

        marcaEntity.setNome(marcaDto.nome());

        marcaRepository.save(marcaEntity);
    }

    private MarcaDto criarMarcaDto(MarcaEntity marcaEntity) {
        return new MarcaDto(
                marcaEntity.getId(),
                marcaEntity.getNome()
        );
    }
    
    @Override
	public List<DashboardMarcaProjecao> getSumarioMarcas() {
		return marcaRepository.getSumarioMarcas();
	}
}