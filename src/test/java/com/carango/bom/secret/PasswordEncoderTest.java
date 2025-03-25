
package com.carango.bom.secret;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.carango.bom.dto.LoginDto;


public class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    
    //Teste Responsavel Em tratar salt Senhas e mostrar senha dos usuarios cliptografadas
    @Test
    public void testPasswordEncoding() {
    	
    	List<LoginDto> loginDtos = new ArrayList<LoginDto>();
    	loginDtos.add(new LoginDto("AlanPaiva" , "AlanPaiva@123456"));
    	loginDtos.add(new LoginDto("SamuelS"   , "SamuelS@123456"));
    	loginDtos.add(new LoginDto("SidneiS"   , "SidneiS@123456"));
    	loginDtos.add(new LoginDto("ThiagoH"   , "ThiagoH@123456"));
    	loginDtos.add(new LoginDto("TiagoG"    , "TiagoG@123456"));
    	loginDtos.add(new LoginDto("WiliamN"   , "WiliamN@123456"));
    	
    	for (LoginDto loginDto : loginDtos) {
    		
    		String hashPassword = passwordEncoder.encode(loginDto.getPassword());
    		assertTrue(passwordEncoder.matches(loginDto.getPassword(), hashPassword));
    		
    		System.out.println("User: " + loginDto.getUsername());
    		System.out.println("Encoded Password: " + hashPassword);
		}
    }
    
}
