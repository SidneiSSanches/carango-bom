
package com.carango.bom.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carango.bom.repository.marca.MarcaVeiculoRepository;
import com.carango.bom.repository.marca.entity.MarcaEntity;

import lombok.RequiredArgsConstructor;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class MarcaVeiculoRepositoryTest {

	@Mock
	private MarcaVeiculoRepository marcaVeiculoRepository;

	@Test
	void testFindByNome() {
		// Arrange
		MarcaEntity marca = new MarcaEntity();
		marca.setNome("Toyota");
		when(marcaVeiculoRepository.findByNome("Toyota")).thenReturn(Optional.of(marca));

		// Act
		Optional<MarcaEntity> foundMarca = marcaVeiculoRepository.findByNome("Toyota");

		// Assert
		assertThat(foundMarca).isPresent();
		assertThat(foundMarca.get().getNome()).isEqualTo("Toyota");
	}
}
