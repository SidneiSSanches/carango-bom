package com.carango.bom.controller.handler.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SuccessResponseHandler {

    public ResponseEntity<Map<String, String>> ok(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> created(String resourceLocation, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", message);
        response.put("localizacao", resourceLocation);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", resourceLocation);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}
