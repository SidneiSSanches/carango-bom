package com.carango.bom.controller;

import com.carango.bom.dto.AuthenticationRequestDto;
import com.carango.bom.dto.JwtDto;
import com.carango.bom.service.impl.UserServiceImpl;
import com.carango.bom.utils.JwTokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private JwTokenUtils jwTokenUtil;

    @InjectMocks
    private AuthenticationController authenticationController;

    private AuthenticationRequestDto authenticationRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationRequest = new AuthenticationRequestDto("root", "password");
    }

    @Test
    void criarAuthenticationToken() throws Exception {
        String username = authenticationRequest.getUsername();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        UserDetails userDetails = mock(UserDetails.class);
        when(userServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
        String token = "mock-jwt-token";
        when(jwTokenUtil.generateToken(userDetails)).thenReturn(token);

        ResponseEntity<?> responseEntity = authenticationController.createAuthenticationToken(authenticationRequest);

        assertEquals(200, responseEntity.getStatusCodeValue()); // HTTP Status 200 OK
        JwtDto jwtDto = (JwtDto) responseEntity.getBody();
        assertNotNull(jwtDto);
        assertEquals(token, jwtDto.getJwt());
    }

    @Test
    void erroQuandoAutenticacaoFalhar() throws Exception {
        String username = authenticationRequest.getUsername();
        doThrow(new RuntimeException("Authentication failed")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticationController.createAuthenticationToken(authenticationRequest);
        });

        assertEquals("Authentication failed", exception.getMessage());
    }
}
