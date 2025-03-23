
package com.carango.bom.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.carango.bom.repository.user.UserRepository;
import com.carango.bom.repository.user.entity.UserEntity;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private UserEntity userEntity;

	@BeforeEach
	void setUp() {
		userEntity = new UserEntity(1l, "testuser", "password");
	}

	@Test
	void testLoadUserByUsername_Success() {
		when(userRepository.findByLogin("testuser")).thenReturn(userEntity);

		UserDetails userDetails = userService.loadUserByUsername("testuser");

		assertThat(userDetails).isNotNull();
		assertThat(userDetails.getUsername()).isEqualTo("testuser");
		assertThat(userDetails.getPassword()).isEqualTo("password");
		assertThat(userDetails.getAuthorities()).isEqualTo(Collections.unmodifiableSet(Collections.emptySet()));
	}

	@Test
	void testLoadUserByUsername_UserNotFound() {
		when(userRepository.findByLogin("testuser")).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("testuser"));
	}
}
