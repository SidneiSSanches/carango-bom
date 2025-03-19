package com.carango.bom.controller;

import com.carango.bom.dto.CustomerRequest;
import com.carango.bom.service.impl.LoginServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;

    @PostMapping("/login")
    @Operation(summary="Gera Token para acesso",tags="Token Login",description="O token gerado é utilizado para acesso a demais funcionalidades")
    public String login(@Valid @RequestBody CustomerRequest request){
        return loginServiceImpl.validaLogin(request);
    }
}
