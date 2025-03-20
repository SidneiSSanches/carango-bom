
package com.carango.bom.secret;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.carango.bom.dto.AuthenticationRequestDto;


public class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    
    //Teste Responsavel Em tratar salt Senhas e mostrar senha dos usuarios cliptografadas
    @Test
    public void testPasswordEncoding() {
    	
    	List<AuthenticationRequestDto> authenticationRequestDtos = new ArrayList<AuthenticationRequestDto>();
    	authenticationRequestDtos.add(new AuthenticationRequestDto("AlanPaiva" , "AlanPaiva@123456"));
    	authenticationRequestDtos.add(new AuthenticationRequestDto("SamuelS"   , "SamuelS@123456"));
    	authenticationRequestDtos.add(new AuthenticationRequestDto("SidneiS"   , "SidneiS@123456"));
    	authenticationRequestDtos.add(new AuthenticationRequestDto("ThiagoH"   , "ThiagoH@123456"));
    	authenticationRequestDtos.add(new AuthenticationRequestDto("TiagoG"    , "TiagoG@123456"));
    	authenticationRequestDtos.add(new AuthenticationRequestDto("WiliamN"   , "WiliamN@123456"));
    	
    	for (AuthenticationRequestDto authenticationRequestDto : authenticationRequestDtos) {
    		
    		String hashPassword = passwordEncoder.encode(authenticationRequestDto.getPassword());	
    		assertTrue(passwordEncoder.matches(authenticationRequestDto.getPassword(), hashPassword));
    		
    		System.out.println("User: " + authenticationRequestDto.getUsername());
    		System.out.println("Encoded Password: " + hashPassword);
		}
    }
    
}
