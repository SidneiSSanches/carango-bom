package com.carango.bom.controller;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.NovoVeiculoDto;

import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.service.VeiculoService;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public static final Long ID_MARCA = 1L;
    public static final Long ID_VEICULO = 1L;

    @Test
    void listarVeiculos() {
        Pageable paginacao = Pageable.unpaged();
        Page<VeiculoDto> veiculos = mock(Page.class);
        when(veiculoService.listarVeiculos(any(Pageable.class), any(FiltroBuscaVeiculoDto.class))).thenReturn(veiculos);

        var resultado = veiculoController.listarVeiculos(paginacao, null, null, null);

        assertNotNull(resultado);
    }

    @Test
    void listarPorMarca() {
        Pageable paginacao = Pageable.unpaged();
        Page<VeiculoDto> veiculos = mock(Page.class);
        when(veiculoService.listarVeiculos(any(Pageable.class), any(FiltroBuscaVeiculoDto.class))).thenReturn(veiculos);

        var resultado = veiculoController.listarVeiculos(paginacao, ID_MARCA, null, null);

        assertNotNull(resultado);
    }

    @Test
    void listarPorFaixaDePreco() {
        BigDecimal valorMinimo = BigDecimal.valueOf(5000);
        BigDecimal valorMaximo = BigDecimal.valueOf(20000);
        Pageable paginacao = Pageable.unpaged();
        Page<VeiculoDto> veiculos = mock(Page.class);
        when(veiculoService.listarVeiculos(any(Pageable.class), any(FiltroBuscaVeiculoDto.class))).thenReturn(veiculos);

        var resultado = veiculoController.listarVeiculos(paginacao, null, valorMinimo, valorMaximo);

        assertNotNull(resultado);
    }

    @Test
    void criarVeiculo() {
        var marca = new MarcaDto(1L, "Toyota");
        NovoVeiculoDto novoVeiculoDto = new NovoVeiculoDto(1L, "Corolla", 2023, new BigDecimal("100000"));
        VeiculoDto veiculoDto = new VeiculoDto(1L, marca, "Corolla", 2023, new BigDecimal("100000"));
        when(veiculoService.criarVeiculo(any(NovoVeiculoDto.class))).thenReturn(veiculoDto);
        ResponseEntity<Object> resposta = veiculoController.criarVeiculo(novoVeiculoDto);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void atualizarVeiculo() {
        NovoVeiculoDto novoVeiculoDto = mock(NovoVeiculoDto.class);
        ResponseEntity<Object> resposta = veiculoController.atualizarVeiculo(ID_VEICULO, novoVeiculoDto);
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void removerVeiculo() {
        ResponseEntity<Object> resposta = veiculoController.removerVeiculo(ID_VEICULO);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    void removerVeiculo_DeveLancarExceptionQuandoVeiculoNaoExistir() {
        Long veiculoIdInexistente = 99L;
        doThrow(new EntityNotFoundException("Veículo não encontrado")).when(veiculoService).removerVeiculo(veiculoIdInexistente);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            veiculoController.removerVeiculo(veiculoIdInexistente);
        });

        assertEquals("Veículo não encontrado", exception.getMessage());
    }
}