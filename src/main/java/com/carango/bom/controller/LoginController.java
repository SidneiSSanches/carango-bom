package com.carango.bom.controller;

import com.carango.bom.request.CustomerRequest;
import com.carango.bom.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody CustomerRequest request){
        return loginService.validaLogin(request);
    }
}
