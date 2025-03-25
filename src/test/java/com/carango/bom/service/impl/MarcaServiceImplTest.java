
package com.carango.bom.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.MarcaVeiculoRepository;
import com.carango.bom.repository.marca.entity.MarcaEntity;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class MarcaServiceImplTest {

    @Mock
    private MarcaVeiculoRepository repository;

    @InjectMocks
    private MarcaServiceImpl service;

    private MarcaEntity marcaEntity;
    private MarcaDto marcaDto;

    @BeforeEach
    void setUp() {
        marcaEntity = MarcaEntity.builder()
                .id(1L)
                .nome("Toyota")
                .build();

        marcaDto = new MarcaDto(1L, "Toyota");
    }

    @Test
    void testListarTodas() {
        PageRequest paginacao = PageRequest.of(0, 10);
        Page<MarcaEntity> page = new PageImpl<>(List.of(marcaEntity));

        when(repository.findAll(paginacao)).thenReturn(page);

        Page<MarcaDto> result = service.listarTodas(paginacao);

        assertThat(result).isNotEmpty();
        assertThat(result.getContent()).contains(marcaDto);
    }

    @Test
    void testBuscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(marcaEntity));

        var result = service.buscarPorId(1L);

        assertThat(result).isEqualTo(marcaDto);
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void testCriarMarca() {
        service.criarMarca(marcaDto);

        verify(repository, times(1)).save(any(MarcaEntity.class));
    }

    @Test
    void testExcluir() {
        service.excluir(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testAtualizarMarca() {
        service.criarMarca(marcaDto);

        verify(repository, times(1)).save(any(MarcaEntity.class));
    }
}