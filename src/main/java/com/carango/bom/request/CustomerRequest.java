package com.carango.bom.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequest {

    @NotBlank(message = "O campo de usuário não pode estar vazio.")
    @Size(min = 3, max = 30, message = "O nome de usuário deve ter entre 3 e 30 caracteres.")
    String name;

    @NotBlank(message = "O campo de senha não pode estar vazio.")
    @Size(min = 6, max = 8, message = "A senha deve ter entre 6 e 8 caracteres.")
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
