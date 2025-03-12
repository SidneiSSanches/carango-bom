package com.carango.bom.service;

import com.carango.bom.request.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public String validaLogin(CustomerRequest request) {
        //TODO token JWT
        if (request.getPassword().equals("1")){
            return "sucesso";
        }
        return "erro";
    }
}
