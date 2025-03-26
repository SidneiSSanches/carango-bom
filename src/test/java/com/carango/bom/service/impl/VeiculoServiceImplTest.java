package com.carango.bom.service.impl;

import static com.carango.bom.utils.ConverteDtoParaEntityUtils.paraMarcaEntity;
import static com.carango.bom.utils.ConverteEntityParaDtoUtils.paraVeiculoDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.VeiculoDto;
import com.carango.bom.service.MarcaService;
import com.carango.bom.service.strategy.veiculo.FiltroBuscaVeiculoStrategy;
import com.carango.bom.service.strategy.veiculo.impl.filtro.FiltroBuscaFaixaValorVeiculoStrategyImpl;
import com.carango.bom.service.strategy.veiculo.impl.filtro.FiltroBuscaMarcaFaixaValorVeiculoStrategyImpl;
import com.carango.bom.service.strategy.veiculo.impl.filtro.FiltroBuscaMarcaVeiculoStrategyImpl;
import com.carango.bom.utils.ConverteDtoParaEntityUtils;
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

import com.carango.bom.dto.NovoVeiculoDto;
import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceImplTest {

	@Mock
	private VeiculoRepository veiculoRepository;

	@InjectMocks
	private FiltroBuscaMarcaVeiculoStrategyImpl filtroBuscaMarcaVeiculoStrategy;

	@InjectMocks
	private FiltroBuscaFaixaValorVeiculoStrategyImpl filtroBuscaFaixaValorVeiculoStrategy;

	@InjectMocks
	private FiltroBuscaMarcaFaixaValorVeiculoStrategyImpl filtroBuscaMarcaFaixaValorVeiculoStrategyImpl;

	@Mock
	private MarcaServiceImpl marcaServiceImpl;

	@InjectMocks
	private VeiculoServiceImpl veiculoService;

	@Mock
	private List<FiltroBuscaVeiculoStrategy> filtroBuscaVeiculoStrategyList;

	private VeiculoEntity veiculoEntity;
	private MarcaEntity marcaEntity;
	private MarcaDto marcaDto;
	private NovoVeiculoDto novoVeiculoDto;
	private VeiculoDto veiculoDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		filtroBuscaVeiculoStrategyList = new ArrayList<>();
		marcaEntity = MarcaEntity.builder().id(1L).nome("Toyota").build();
		marcaDto = new MarcaDto(1L, "Toyota");
		veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
				.valor(new BigDecimal("20000")).build();
		novoVeiculoDto = new NovoVeiculoDto(1L, "Corolla", 2020, new BigDecimal("20000"));
		veiculoDto = new VeiculoDto(1L, new MarcaDto(1L , "Toyota"), "Corolla", 2020, new BigDecimal("20000"));
	}


	@Test
	void testListarVeiculos() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);
		MarcaEntity marcaEntity = ConverteDtoParaEntityUtils.paraMarcaEntity(marcaDto);
		List<VeiculoEntity> mockVeiculos = List.of(
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
						.valor(new BigDecimal("20000")).build(),
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla Novo").ano(2020)
						.valor(new BigDecimal("20000")).build()
		);
		when(veiculoRepository.findAllByValorBetweenAndMarca(any(),any(),any())).thenReturn(mockVeiculos);

		FiltroBuscaVeiculoDto filtroBuscaVeiculoDto = new FiltroBuscaVeiculoDto(1L, new BigDecimal(20000), new BigDecimal(30000));
		Page<VeiculoDto> resultado = filtroBuscaMarcaFaixaValorVeiculoStrategyImpl.filtrar(PageRequest.of(0, 10), filtroBuscaVeiculoDto);
		assertThat(resultado).isNotEmpty();
		assertThat(resultado).contains(veiculoDto);
		assertEquals(resultado.getContent().size(), 2);
	}

	@Test
	void testListarPorMarca() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);
		MarcaEntity marcaEntity = ConverteDtoParaEntityUtils.paraMarcaEntity(marcaDto);
		List<VeiculoEntity> mockVeiculos = List.of(
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
						.valor(new BigDecimal("20000")).build(),
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla Novo").ano(2020)
						.valor(new BigDecimal("20000")).build()
		);
		when(veiculoRepository.findByMarca(any())).thenReturn(mockVeiculos);

		FiltroBuscaVeiculoDto filtroBuscaVeiculoDto = new FiltroBuscaVeiculoDto(1L, null, null);
		Page<VeiculoDto> resultado = filtroBuscaMarcaVeiculoStrategy.filtrar(PageRequest.of(0, 10), filtroBuscaVeiculoDto);
		assertThat(resultado).isNotEmpty();
		assertThat(resultado).contains(veiculoDto);
		assertEquals(resultado.getContent().size(), 2);
	}

	@Test
	void testListarPorFaixaValor() {
		//when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);
		MarcaEntity marcaEntity = ConverteDtoParaEntityUtils.paraMarcaEntity(marcaDto);
		List<VeiculoEntity> mockVeiculos = List.of(
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
						.valor(new BigDecimal("200")).build(),
				veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla Novo").ano(2020)
						.valor(new BigDecimal("200")).build()
		);
		when(veiculoRepository.findAllByValorBetween(any(),any())).thenReturn(mockVeiculos);

		FiltroBuscaVeiculoDto filtroBuscaVeiculoDto = new FiltroBuscaVeiculoDto(null, new BigDecimal(100), new BigDecimal(300));
		Page<VeiculoDto> resultado = filtroBuscaFaixaValorVeiculoStrategy.filtrar(PageRequest.of(0, 10), filtroBuscaVeiculoDto);
		assertThat(resultado).isNotEmpty();
		assertEquals(resultado.getContent().size(), 2);
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