package com.carango.bom.controller;

import com.carango.bom.dto.MarcaDto;
import com.carango.bom.service.impl.MarcaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MarcaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MarcaDto> marcaDtoJson;

    @MockBean
    private MarcaServiceImpl marcaService;

    @Test
    @DisplayName("Deveria devolver codigo http 200 ao listar todas as marcas")
    @WithMockUser
    void listarTodas() throws Exception {
        var response = mvc.perform(get("/marcas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 ao buscar marca por ID")
    @WithMockUser
    void buscarPorId() throws Exception {
        var marcaDto = new MarcaDto(1L, "Marca Teste");
        when(marcaService.buscarPorId(1L)).thenReturn(marcaDto);

        var response = mvc.perform(get("/marcas/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains("Marca Teste");
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 ao criar uma nova marca")
    @WithMockUser
    void criarMarca() throws Exception {
        var marcaDto = new MarcaDto(null, "Nova Marca");
        when(marcaService.criarMarca(any())).thenReturn(new MarcaDto(1L, "Nova Marca"));

        var response = mvc.perform(post("/marcas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(marcaDtoJson.write(marcaDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 204 ao excluir uma marca")
    @WithMockUser
    void excluirMarca() throws Exception {
        var response = mvc.perform(delete("/marcas/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 ao atualizar uma marca")
    @WithMockUser
    void atualizarMarca() throws Exception {
        var marcaDto = new MarcaDto(1L, "Marca Atualizada");
        Mockito.doNothing().when(marcaService).atualizarMarca(Mockito.anyLong(), Mockito.any());

        var response = mvc.perform(put("/marcas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(marcaDtoJson.write(marcaDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 ao consultar o dashboard de marcas")
    @WithMockUser
    void consultaDashboardMarcas() throws Exception {
    //TODO
    }
}
