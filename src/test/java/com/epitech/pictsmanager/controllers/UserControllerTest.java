package com.epitech.pictsmanager.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;

/**
 * Unit tests for the UserController class
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for retrieving all users
     */
    @Test
    public void testGetUsers() {
        List<User> userList = new ArrayList<>();
        when(userService.getUsers()).thenReturn(userList);

        assertEquals(userList, userController.getUsers());
    }

    /**
     * Test method for retrieving a user by ID
     */
    @Test
    public void testGetUserById() {
        String token = "dummyToken";
        User dummyUser = new User();
        dummyUser.setId(1L);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUser(token)).thenReturn(dummyUser);
        when(userService.getUserById(dummyUser.getId())).thenReturn(dummyUser);

        ResponseEntity<User> response = userController.getUserById(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyUser, response.getBody());
    }

    /**
     * Test method for updating a user
     */
    @Test
    public void testUpdateUser() {
        String token = "dummyToken";
        User dummyUser = new User();
        dummyUser.setId(1L);
        dummyUser.setPassword("oldPassword"); 

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUser(token)).thenReturn(dummyUser);
        when(userService.getUserById(dummyUser.getId())).thenReturn(dummyUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User updatedUser = new User();
        updatedUser.setNom("UpdatedName");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("newPassword");

        System.out.println("Mot de passe avant codage : " + updatedUser.getPassword());
        ResponseEntity<?> response = userController.updateUser(updatedUser, request);
        System.out.println("Mot de passe après codage : " + dummyUser.getPassword());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dummyUser.getNom(), updatedUser.getNom());
        assertEquals(dummyUser.getEmail(), updatedUser.getEmail());
    }

    /**
     * Test method for saving a new user
     */
    @Test
    public void testSaveUser() {
        User newUser = new User();
        newUser.setEmail("new@example.com");
        newUser.setPassword("password");

        when(userService.isEmailAlreadyExists(newUser.getEmail())).thenReturn(false);

        ResponseEntity<?> response = userController.saveUser(newUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
