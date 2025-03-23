package com.carango.bom.controller;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.service.impl.MarcaServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MarcaControllerTest {

    @Mock
    private MarcaServiceImpl service;

    @InjectMocks
    private MarcaController marcaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public static final Long ID_MARCA = 1L;

    @Test
    void listarTodasAsMarcas() {
        MarcaEntity marca1 = new MarcaEntity();
        marca1.setNome("Toyota");
        MarcaEntity marca2 = new MarcaEntity();
        marca2.setNome("Ford");
        Pageable pageable = PageRequest.of(0, 10);
        List<MarcaEntity> marcas = List.of(marca1, marca2);
        Page<MarcaEntity> page = new PageImpl<>(marcas, pageable, marcas.size());
        when(service.listarTodas(pageable)).thenReturn(page);

        Page<MarcaEntity> resultado = marcaController.listarTodas(pageable);

        assertNotNull(resultado);
        assertEquals(2, resultado.getTotalElements());
        assertEquals("Toyota", resultado.getContent().get(0).getNome());
    }

    @Test
    void buscarIdMarca() {
        MarcaEntity marca = new MarcaEntity();
        marca.setNome("Toyota");
        when(service.buscarPorId(ID_MARCA)).thenReturn(marca);

        ResponseEntity<MarcaDto> resposta = marcaController.buscarPorId(ID_MARCA);
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("Toyota", resposta.getBody().nome());

        verify(service, times(1)).buscarPorId(ID_MARCA);
    }

    @Test
    void buscarIdNaoExistente() {
        Long idInexistente = 99L;
        when(service.buscarPorId(idInexistente)).thenThrow(new EntityNotFoundException("MarcaVeiculo com ID " + idInexistente + " não foi encontrada."));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            marcaController.buscarPorId(idInexistente);
        });

        assertEquals("MarcaVeiculo com ID 99 não foi encontrada.", exception.getMessage());
    }

    @Test
    void criarMarca() {
        MarcaDto novaMarca = new MarcaDto("Honda");
        ResponseEntity<Object> resposta = marcaController.criarMarca(novaMarca);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void excluirSucesso() {
        marcaController.excluir(ID_MARCA);
        verify(service, times(1)).excluir(ID_MARCA);
    }

    @Test
    void atualizarMarca() {
        MarcaDto marcaAtualizada = new MarcaDto("Honda Atualizada");
        ResponseEntity expectedResponse = ResponseEntity.ok().build();
        when(service.atualizarMarca(ID_MARCA, marcaAtualizada)).thenReturn(expectedResponse);

        ResponseEntity resposta = marcaController.atualizarMarca(ID_MARCA, marcaAtualizada);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}
