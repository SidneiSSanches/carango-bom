package com.carango.bom.service.impl;

import static com.carango.bom.utils.ConverteEntityParaDtoUtils.paraVeiculoDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceImplTest {

	@Mock
	private VeiculoRepository veiculoRepository;

	@Mock
	private MarcaServiceImpl marcaServiceImpl;

	@InjectMocks
	private VeiculoServiceImpl veiculoService;

	private VeiculoEntity veiculoEntity;
	private MarcaEntity marcaEntity;
	private MarcaDto marcaDto;
	private NovoVeiculoDto novoVeiculoDto;
	private VeiculoDto veiculoDto;
	private List<FiltroBuscaVeiculoStrategy> filtroBuscaVeiculoStrategyList;


	@BeforeEach
	void setUp() {
		marcaEntity = MarcaEntity.builder().id(1L).nome("Toyota").build();
		marcaDto = new MarcaDto(1L, "Toyota");
		veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
				.valor(new BigDecimal("20000")).build();
		novoVeiculoDto = new NovoVeiculoDto(1L, "Corolla", 2020, new BigDecimal("20000"));
		veiculoDto = new VeiculoDto(1L, new MarcaDto(1L , "Toyota"), "Corolla", 2020, new BigDecimal("20000"));
	}

	
	@Test
	void testListarVeiculos() {
		Pageable paginacao = PageRequest.of(0, 10);
		FiltroBuscaVeiculoDto filtro = new FiltroBuscaVeiculoDto(1L,new BigDecimal("1"),new BigDecimal(10000));
		Page<VeiculoDto> veiculosPaginados = new PageImpl<>(List.of(paraVeiculoDto(veiculoEntity)));

		when(filtroBuscaVeiculoStrategyList.get(0).filtrar(paginacao, filtro)).thenReturn(veiculosPaginados);

		Page<VeiculoDto> resultado = veiculoService.listarVeiculos(paginacao, filtro);

		assertNotNull(resultado);
		assertEquals(1, resultado.getContent().size());
	}

	@Test
	void testListarPorMarca() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);
		when(veiculoRepository.findByMarca(marcaEntity)).thenReturn(List.of(veiculoEntity));

		var result = veiculoService.listarVeiculos(Pageable.unpaged(), new FiltroBuscaVeiculoDto(1L, null, null));

		assertThat(result).isNotEmpty();
		assertThat(result).contains(veiculoDto);
	}

	@Test
	void testListarPorFaixaValor() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);
		when(veiculoRepository.findByMarca(marcaEntity)).thenReturn(List.of(veiculoEntity));

		var result = veiculoService.listarVeiculos(Pageable.unpaged(), new FiltroBuscaVeiculoDto(null, new BigDecimal("20000"), new BigDecimal("20000")));

		assertThat(result).isNotEmpty();
		assertThat(result).contains(veiculoDto);
	}

	@Test
	void testCriarVeiculo() {
		when(marcaServiceImpl.buscarPorId(novoVeiculoDto.marcaId())).thenReturn(marcaDto);
		when(veiculoRepository.save(any(VeiculoEntity.class))).thenReturn(veiculoEntity);
		VeiculoDto veiculoCriado = veiculoService.criarVeiculo(novoVeiculoDto);
		assertNotNull(veiculoCriado);
		assertEquals("Corolla", veiculoCriado.modelo());
	}

	@Test
	void testAtualizarVeiculo() {
		when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoEntity));
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);

		veiculoService.atualizarVeiculo(1L, novoVeiculoDto);

		verify(veiculoRepository, times(1)).save(any(VeiculoEntity.class));
	}


	@Test
	void testRemoverVeiculo() {
		when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoEntity));

		veiculoService.removerVeiculo(1L);

		verify(veiculoRepository, times(1)).delete(veiculoEntity);
	}
}