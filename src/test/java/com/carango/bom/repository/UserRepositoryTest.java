
package com.carango.bom.repository;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carango.bom.repository.user.UserRepository;
import com.carango.bom.repository.user.entity.UserEntity;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByLogin() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setLogin("john_doe");
        when(userRepository.findByLogin("john_doe")).thenReturn(user);

        // Act
        UserEntity foundUser = userRepository.findByLogin("john_doe");

        // Assert
        assert foundUser != null;
        assert "john_doe".equals(foundUser.getLogin());
    }
}
