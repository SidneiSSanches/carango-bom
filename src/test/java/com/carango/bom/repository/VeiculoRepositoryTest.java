
package com.carango.bom.repository;

import com.carango.bom.repository.marca.entity.MarcaEntity;
import com.carango.bom.repository.veiculo.VeiculoRepository;
import com.carango.bom.repository.veiculo.entity.VeiculoEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VeiculoRepositoryTest {

	@Mock
	private VeiculoRepository veiculoRepository;

	@Test
	void testFindByMarca() {
		// Arrange
		MarcaEntity marca = new MarcaEntity();
		marca.setId(1L);
		marca.setNome("Toyota");

		VeiculoEntity veiculo = new VeiculoEntity();
		veiculo.setId(1L);
		veiculo.setMarca(marca);
		veiculo.setModelo("Corolla");
		veiculo.setAno(2020);
		veiculo.setValor(new BigDecimal("15000"));

		when(veiculoRepository.findByMarca(marca)).thenReturn(List.of(veiculo));

		// Act
		List<VeiculoEntity> foundVeiculos = veiculoRepository.findByMarca(marca);

		// Assert
		assertThat(foundVeiculos).isNotEmpty();
		assertThat(foundVeiculos).contains(veiculo);
	}

	@Test
	void testFindAllByValorBetween() {
		// Arrange
		BigDecimal valorMinimo = new BigDecimal("10000");
		BigDecimal valorMaximo = new BigDecimal("20000");

		VeiculoEntity veiculo = new VeiculoEntity();
		veiculo.setId(1L);
		veiculo.setModelo("Corolla");
		veiculo.setAno(2020);
		veiculo.setValor(new BigDecimal("15000"));

		when(veiculoRepository.findAllByValorBetween(valorMinimo, valorMaximo)).thenReturn(List.of(veiculo));

		// Act
		List<VeiculoEntity> foundVeiculos = veiculoRepository.findAllByValorBetween(valorMinimo, valorMaximo);

		// Assert
		assertThat(foundVeiculos).isNotEmpty();
		assertThat(foundVeiculos).contains(veiculo);
	}
}
