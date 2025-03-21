
package com.carango.bom.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.carango.bom.util.JwTokenUtil;


@RestController
public class AuthenticationController implements AuthenticationSwaggerController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JwTokenUtil jwTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest)
			throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));

		final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtDto(jwt));
	}
}
