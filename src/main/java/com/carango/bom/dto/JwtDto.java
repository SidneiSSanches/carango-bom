
package com.carango.bom.dto;

public class JwtDto {
    private final String jwt;

    public JwtDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
