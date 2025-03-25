
package com.carango.bom.controller;

import com.carango.bom.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.carango.bom.controller.swagger.UsuarioSwaggerController;
import com.carango.bom.dto.LoginDto;
import com.carango.bom.dto.JwtDto;
import com.carango.bom.service.impl.UserServiceImpl;
import com.carango.bom.utils.JwTokenUtils;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioSwaggerController {
	private AuthenticationManager authenticationManager;
	private UserServiceImpl userServiceImpl;
	private JwTokenUtils jwTokenUtils;

	@PostMapping
	@Override
	public ResponseEntity<JwtDto> logar(@RequestBody LoginDto loginDto) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
						loginDto.getPassword()));

		final UserDetails userDetails = userServiceImpl.loadUserByUsername(loginDto.getUsername());

		final String jwt = jwTokenUtils.generateToken(userDetails);

		return ResponseEntity.ok(new JwtDto(jwt));
	}

	@GetMapping
	@Override
	public ResponseEntity<UsuarioDto> pegarDadosUsuario(@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok()
						.body(
										new UsuarioDto(jwTokenUtils.extractUsername(token.replace("Bearer ", "")))
						);
	}
}