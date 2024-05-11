package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getUsers();

        Assertions.assertEquals(userList.size(), result.size());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Mockito.when(userRepository.findUserById(userId)).thenReturn(user);

        User result = userService.getUserById(userId);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1L);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(user);

        User result = userService.getUserByEmail(email);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testIsEmailAlreadyExists() {
        String existingEmail = "existing@example.com";
        String nonExistingEmail = "new@example.com";

        Mockito.when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new User()));

        boolean existingResult = userService.isEmailAlreadyExists(existingEmail);
        boolean nonExistingResult = userService.isEmailAlreadyExists(nonExistingEmail);

        Assertions.assertTrue(existingResult);
        Assertions.assertFalse(nonExistingResult);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setNom("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.existsById(userId)).thenReturn(true);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.updateUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}