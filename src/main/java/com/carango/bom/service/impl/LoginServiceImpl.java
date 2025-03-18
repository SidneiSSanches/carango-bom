package com.carango.bom.service.impl;

import com.carango.bom.dto.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
    public String validaLogin(CustomerRequest request) {
        //TODO token JWT
        if (request.getPassword().equals("1")){
            return "sucesso";
        }
        return "erro";
    }
}