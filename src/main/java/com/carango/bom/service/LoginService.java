package com.carango.bom.service;

import com.carango.bom.dto.CustomerRequest;

public interface LoginService {
  String validaLogin(CustomerRequest request);
}