package com.carango.bom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    @Schema(description="Campo de usuário" ,example= "Solano",requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "O campo de usuário não pode estar vazio.")
    @Size(min = 3, max = 30, message = "O nome de usuário deve ter entre 3 e 30 caracteres.")
    private String name;

    @Schema(description="Campo de senha" ,example= "123456",requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "O campo de senha não pode estar vazio.")
    @Size(min = 6, max = 8, message = "A senha deve ter entre 6 e 8 caracteres.")
    private String password;
}