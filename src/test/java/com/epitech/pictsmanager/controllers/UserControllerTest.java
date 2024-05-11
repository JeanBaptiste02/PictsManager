package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.controllers.UserController;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	 @Autowired
	 private MockMvc mockMvc;

	 @Autowired
	    private UserController userController;
	    private UserService userService;
	    @Mock
	    private JwtUtil jwtUtil;
	    private PasswordEncoder passwordEncoder;

	    @BeforeEach
	    public void initMocks() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    @BeforeEach
	    public void setup() {
	    	userController = new UserController();
	        userService = Mockito.mock(UserService.class);
	        jwtUtil = Mockito.mock(JwtUtil.class);
	        passwordEncoder = Mockito.mock(PasswordEncoder.class);
	        MockitoAnnotations.openMocks(this);
	    }

    @Test
    public void testGetUsers_PositiveScenario() {
        List<User> mockUsers = Arrays.asList(
        		new User("Ganesh", "ganesh@example.com", "password"),
                new User("Ramesh", "ramesh@example.com", "password")
        );

        when(userService.getUsers()).thenReturn(mockUsers);

        List<User> users = userController.getUsers();

        assertEquals(2, users.size());
        assertEquals("Ganesh", users.get(0).getNom());
        assertEquals("Ramesh", users.get(1).getNom());
    }

    @Test
    public void testGetUsers_NegativeScenario() {
    	when(userService.getUsers()).thenReturn(Collections.emptyList());

        List<User> users = userController.getUsers();

        assertTrue(users.isEmpty());
    }

    @Test
    public void testGetUserById_WithValidToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");

        when(jwtUtil.extractUser("valid_token")).thenReturn(new User());

        ResponseEntity<User> response = userController.getUserById(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }


    @Test
    public void testGetUserById_WithInvalidToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid_token");

        when(jwtUtil.extractUser("invalid_token")).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(request);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateUser_PositiveScenario() {
        User user = new User("Ram", "ram@example.com", "password");
        User existingUser = new User("Existing", "existing@example.com", "existingpassword");
        String token = "token";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        when(jwtUtil.extractUser(token)).thenReturn(existingUser);
        when(userService.getUserById(existingUser.getId())).thenReturn(existingUser);

        ResponseEntity<?> response = userController.updateUser(user, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUser, response.getBody());
    }

    @Test
    public void testUpdateUser_NegativeScenario() {
        User user = new User("Ram", "ram@example.com", "password");
        String token = "token";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        when(jwtUtil.extractUser(token)).thenReturn(null);

        ResponseEntity<?> response = userController.updateUser(user, request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized", response.getBody());
    }

    @Test
    public void testSaveUser_PositiveScenario() {
        User user = new User("Bharat", "bharat@example.com", "password");

        when(userService.isEmailAlreadyExists(user.getEmail())).thenReturn(false);

        ResponseEntity<?> response = userController.saveUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User has been added", response.getBody());
    }

    @Test
    public void testSaveUser_NegativeScenario() {
        User user = new User("Bharat", "bharat@example.com", "password");

        when(userService.isEmailAlreadyExists(user.getEmail())).thenReturn(true);

        ResponseEntity<?> response = userController.saveUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("This email already exists", response.getBody());
    }

    @Test
    public void testExtractTokenFromRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer token123");

        String extractedToken = userController.extractTokenFromRequest(request);

        assertEquals("token123", extractedToken);
    }

}
