package com.carango.bom.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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

import com.carango.bom.dto.InclusaoMarcaDto;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.service.impl.MarcaServiceImpl;

import jakarta.persistence.EntityNotFoundException;

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
        var marca1 = new MarcaDto(1L, "Toyota");
        var marca2 = new MarcaDto(2L, "Ford");

        Pageable pageable = PageRequest.of(0, 10);
        List<MarcaDto> marcas = List.of(marca1, marca2);
        Page<MarcaDto> page = new PageImpl<>(marcas, pageable, marcas.size());
        when(service.listarTodas(pageable)).thenReturn(page);

        var resultado = marcaController.listarTodas(pageable);

        assertNotNull(resultado);
        assertEquals(2, resultado.getTotalElements());
        assertEquals("Toyota", resultado.getContent().get(0).nome());
    }

    @Test
    void buscarIdMarca() {
        var marca = new MarcaDto(1L, "Toyota");

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
        InclusaoMarcaDto inclusaoMarcaDto = new InclusaoMarcaDto(1L, "Honda");
        when(service.criarMarca(any(MarcaDto.class))).thenReturn(inclusaoMarcaDto.toMarcaDto(inclusaoMarcaDto));
        ResponseEntity<Object> resposta = marcaController.criarMarca(inclusaoMarcaDto);
        assertNotNull(resposta);
        assertEquals("/marcas/1", resposta.getHeaders().get("Location").get(0));
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void excluirSucesso() {
        marcaController.excluirMarca(ID_MARCA);
        verify(service, times(1)).excluir(ID_MARCA);
    }

    @Test
    void atualizarMarca() {
    	InclusaoMarcaDto inclusaoMarcaDto = new InclusaoMarcaDto(1L, "Honda Atualizada");
        doNothing().when(service).atualizarMarca(anyLong(), any(MarcaDto.class));

        var resposta = marcaController.atualizarMarca(ID_MARCA, inclusaoMarcaDto);

        assertEquals(200, resposta.getStatusCodeValue());
    }
}
