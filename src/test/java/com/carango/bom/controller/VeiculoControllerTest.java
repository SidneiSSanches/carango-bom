package com.carango.bom.controller;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;
import com.carango.bom.service.VeiculoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

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
        Page<VeiculoEntity> veiculos = mock(Page.class);
        when(veiculoService.listarVeiculos(paginacao)).thenReturn(veiculos);

        Page<VeiculoEntity> resultado = veiculoController.listarVeiculos(paginacao);

        assertNotNull(resultado);
    }

    @Test
    void listarPorMarca() {
        List<VeiculoEntity> veiculos = mock(List.class);
        when(veiculoService.listarPorMarca(ID_MARCA)).thenReturn(veiculos);

        ResponseEntity<List<VeiculoEntity>> resposta = veiculoController.listarPorMarca(ID_MARCA);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    void listarPorFaixaDePreco() {
        BigDecimal valorMinimo = BigDecimal.valueOf(5000);
        BigDecimal valorMaximo = BigDecimal.valueOf(20000);
        List<VeiculoEntity> veiculos = mock(List.class);
        when(veiculoService.listarPorFaixaValor(valorMinimo, valorMaximo)).thenReturn(veiculos);

        ResponseEntity<List<VeiculoEntity>> resposta = veiculoController.listarPorFaixa(valorMinimo, valorMaximo);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    void criarVeiculo() {
        NovoVeiculoDto novoVeiculoDto = mock(NovoVeiculoDto.class);
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
