package com.carango.bom.controller;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.service.VeiculoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class VeiculoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<NovoVeiculoDto> novoVeiculoJson;

    @Autowired
    private JacksonTester<List<VeiculoDto>> veiculoListaJson;

    @MockBean
    private VeiculoService veiculoService;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 ao listar veiculos")
    @WithMockUser
    void listarVeiculos() throws Exception {
        var response = mvc.perform(get("/veiculos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver código HTTP 201 ao criar um veículo")
    @WithMockUser
    void criarVeiculo() throws Exception {
        // Cria um NovoVeiculoDto para simular o corpo da requisição
        var novoVeiculoDto = new NovoVeiculoDto(1L, "Modelo", 2022, BigDecimal.valueOf(40000));

        // Cria um VeiculoDto para simular o retorno do serviço
        var veiculoDto = new VeiculoDto(1L, null, "Modelo", 2022, BigDecimal.valueOf(40000));

        // Configura o mock do veiculoService para retornar o VeiculoDto
        when(veiculoService.criarVeiculo(any(NovoVeiculoDto.class))).thenReturn(veiculoDto);

        // Executa a requisição POST e verifica a resposta
        var response = mvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoVeiculoJson.write(novoVeiculoDto).getJson()))
                .andReturn().getResponse();

        // Verifica se o status da resposta é HTTP 201 (CREATED)
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    
    
    @Test
    @DisplayName("Deveria devolver codigo HTTP 200 ao atualizar um veiculo")
    @WithMockUser
    void atualizarVeiculo() throws Exception {
        var novoVeiculoDto = new NovoVeiculoDto(9L,"Modelo Novo", 2023, BigDecimal.valueOf(50000));

        var response = mvc.perform(put("/veiculos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoVeiculoJson.write(novoVeiculoDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 204 ao remover um veiculo")
    @WithMockUser
    void removerVeiculo() throws Exception {
        var response = mvc.perform(delete("/veiculos/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
