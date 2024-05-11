package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserService userService;

    public UserRepositoryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Mockito.when(userRepository.findUserById(userId)).thenReturn(user);

        User result = userRepository.findUserById(userId);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(user);

        User result = userRepository.findUserByEmail(email);

        Assertions.assertEquals(user, result);
    }


    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findByEmail(email);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(user, result.get());
    }
}
