package com.carango.bom.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
	private NovoVeiculoDto novoVeiculoDto;

	@BeforeEach
	void setUp() {
		marcaEntity = MarcaEntity.builder().id(1L).nome("Toyota").build();
		veiculoEntity = VeiculoEntity.builder().id(1L).marca(marcaEntity).modelo("Corolla").ano(2020)
				.valor(new BigDecimal("20000")).build();
		novoVeiculoDto = new NovoVeiculoDto(1L, "Corolla", 2020, new BigDecimal("20000"));
	}

	
	@Test
	void testListarVeiculos() {
		Pageable paginacao = PageRequest.of(0, 10);
		Page<VeiculoEntity> page = new PageImpl<>(List.of(veiculoEntity));

		when(veiculoRepository.findAll(paginacao)).thenReturn(page);

		Page<VeiculoEntity> result = veiculoService.listarVeiculos(paginacao);

		assertThat(result).isNotEmpty();
		assertThat(result.getContent()).contains(veiculoEntity);
	}

	@Test
	void testListarPorMarca() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaEntity);
		when(veiculoRepository.findByMarca(marcaEntity)).thenReturn(List.of(veiculoEntity));

		List<VeiculoEntity> result = veiculoService.listarPorMarca(1L);

		assertThat(result).isNotEmpty();
		assertThat(result).contains(veiculoEntity);
	}

	@Test
	void testListarPorFaixaValor() {
		when(veiculoRepository.findAllByValorBetween(new BigDecimal("10000"), new BigDecimal("30000")))
				.thenReturn(List.of(veiculoEntity));

		List<VeiculoEntity> result = veiculoService.listarPorFaixaValor(new BigDecimal("10000"),
				new BigDecimal("30000"));

		assertThat(result).isNotEmpty();
		assertThat(result).contains(veiculoEntity);
	}

	@Test
	void testCriarVeiculo() {
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaEntity);

		veiculoService.criarVeiculo(novoVeiculoDto);

		verify(veiculoRepository, times(1)).save(any(VeiculoEntity.class));
	}

	@Test
	void testAtualizarVeiculo() {
		when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoEntity));
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaEntity);

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