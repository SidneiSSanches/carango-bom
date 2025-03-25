package com.carango.bom.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.carango.bom.dto.FiltroBuscaVeiculoDto;
import com.carango.bom.dto.MarcaDto;
import com.carango.bom.dto.VeiculoDto;
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
		Page<VeiculoEntity> page = new PageImpl<>(List.of(veiculoEntity));
		FiltroBuscaVeiculoDto filtroBuscaVeiculoDto = mock(FiltroBuscaVeiculoDto.class);

		when(veiculoRepository.findAll(paginacao)).thenReturn(page);

		var result = veiculoService.listarVeiculos(paginacao, filtroBuscaVeiculoDto);

		assertThat(result).isNotEmpty();
		assertThat(result.getContent()).contains(veiculoDto);
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
		when(marcaServiceImpl.buscarPorId(1L)).thenReturn(marcaDto);

		veiculoService.criarVeiculo(novoVeiculoDto);

		verify(veiculoRepository, times(1)).save(any(VeiculoEntity.class));
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