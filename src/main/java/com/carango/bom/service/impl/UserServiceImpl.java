package com.carango.bom.service.impl;

import java.util.Collections;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carango.bom.repository.user.UserRepository;
import com.carango.bom.repository.user.entity.UserEntity;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("User with login '" + username + "' not found");
		}

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				Collections.emptyList());
	}
}