
package com.carango.bom.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;

import com.carango.bom.filter.JwtRequestFilter;

@ExtendWith(MockitoExtension.class)
public class SecurityConfigTest {

    @Mock
    private JwtRequestFilter jwtRequestFilter;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    public void testAuthenticationManagerBean() throws Exception {
        AuthenticationConfiguration authenticationConfiguration = mock(AuthenticationConfiguration.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);

        AuthenticationManager result = securityConfig.authenticationManagerBean(authenticationConfiguration);
        assertThat(result).isNotNull();
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertThat(passwordEncoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    public void testCorsConfigurationSource() {
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        assertThat(corsConfigurationSource).isNotNull();
    }
}
