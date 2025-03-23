package com.carango.bom.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import com.carango.bom.repository.user.entity.UserEntity;

public class UserEntityTest {

	@Test
	public void testUserEntityConstructorAndGetters() {
		UserEntity user = new UserEntity(1L, "testuser", "password");

		assertEquals(1L, user.getId());
		assertEquals("testuser", user.getLogin());
		assertEquals("password", user.getPassword());
	}

	@Test
	public void testUserEntitySetters() {
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setLogin("testuser");
		user.setPassword("password");

		assertEquals(1L, user.getId());
		assertEquals("password", user.getPassword());
	}

	@Test
	public void testUserEntityEqualsAndHashCode() {
		UserEntity user1 = new UserEntity(1L, "testuser", "password");
		UserEntity user2 = new UserEntity(1L, "testuser", "password");
		UserEntity user3 = new UserEntity(2L, "anotheruser", "password");

		assertEquals(user1, user2);
		assertNotEquals(user1, user3);
		assertEquals(user1.hashCode(), user2.hashCode());
		assertNotEquals(user1.hashCode(), user3.hashCode());
	}

	@Test
	public void testUserEntityNotEqualsNull() {
		UserEntity user = new UserEntity(1L, "testuser", "password");

		assertNotEquals(user, null);
	}
}