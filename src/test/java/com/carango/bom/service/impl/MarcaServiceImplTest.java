
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
import org.springframework.http.ResponseEntity;

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

        marcaDto = new MarcaDto("Toyota");
    }

    @Test
    void testListarTodas() {
        PageRequest paginacao = PageRequest.of(0, 10);
        Page<MarcaEntity> page = new PageImpl<>(List.of(marcaEntity));

        when(repository.findAll(paginacao)).thenReturn(page);

        Page<MarcaEntity> result = service.listarTodas(paginacao);

        assertThat(result).isNotEmpty();
        assertThat(result.getContent()).contains(marcaEntity);
    }

    @Test
    void testBuscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(marcaEntity));

        MarcaEntity result = service.buscarPorId(1L);

        assertThat(result).isEqualTo(marcaEntity);
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
        when(repository.findById(1L)).thenReturn(Optional.of(marcaEntity));

        ResponseEntity result = service.atualizarMarca(1L, marcaDto);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        verify(repository, times(1)).save(any(MarcaEntity.class));
    }

    @Test
    void testAtualizarMarcaNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity result = service.atualizarMarca(1L, marcaDto);

        assertThat(result.getStatusCodeValue()).isEqualTo(204);
        verify(repository, never()).save(any(MarcaEntity.class));
    }
}
