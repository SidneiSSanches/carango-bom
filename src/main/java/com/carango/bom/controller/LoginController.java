package com.carango.bom.controller;

import com.carango.bom.dto.CustomerRequest;
import com.carango.bom.service.impl.LoginServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginServiceImpl loginServiceImpl;

    @PostMapping("/login")
    public String login(@Valid @RequestBody CustomerRequest request){
        return loginServiceImpl.validaLogin(request);
    }
}
