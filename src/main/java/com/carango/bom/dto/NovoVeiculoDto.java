package com.carango.bom.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.*;

@JsonRootName(value="Veiculo")
public record NovoVeiculoDto(

		@Schema(description="Campo de id marca" ,example= "99999",requiredMode = RequiredMode.REQUIRED)
        @NotNull(message = "O ID da marca não pode estar nulo.")
        Long idMarca,
		@Schema(description="Campo de modelo" ,example= "Song-Pro",requiredMode = RequiredMode.REQUIRED)
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 ")
        String modelo,
		@Schema(description="Campo de ano" ,example= "2025",requiredMode = RequiredMode.REQUIRED)
        @Positive(message = "O ano deve ser um número positivo.")
        Integer ano,
		@Schema(description="Campo de valor" ,example= "120000",requiredMode = RequiredMode.REQUIRED)
        @Digits(integer = 8, fraction = 2, message = "O preço deve ser valor com duas decimais.")
        BigDecimal valor
) {}