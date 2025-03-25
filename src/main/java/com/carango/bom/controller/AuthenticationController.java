
package com.carango.bom.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carango.bom.controller.swagger.AuthenticationSwaggerController;
import com.carango.bom.dto.AuthenticationRequestDto;
import com.carango.bom.dto.JwtDto;
import com.carango.bom.service.impl.UserServiceImpl;
import com.carango.bom.utils.JwTokenUtils;

@AllArgsConstructor
@RestController
public class AuthenticationController implements AuthenticationSwaggerController{
	private AuthenticationManager authenticationManager;
	private UserServiceImpl userServiceImpl;
	private JwTokenUtils jwTokenUtils;

	@PostMapping("/authenticate")
	@Override
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwTokenUtils.generateToken(userDetails);

		return ResponseEntity.ok(new JwtDto(jwt));
	}
}