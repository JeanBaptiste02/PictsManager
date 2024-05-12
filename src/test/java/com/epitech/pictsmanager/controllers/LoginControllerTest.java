package com.epitech.pictsmanager.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epitech.pictsmanager.dtos.LoginRequest;
import com.epitech.pictsmanager.dtos.LoginResponse;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.service.jwt.UserServiceImp;
import com.epitech.pictsmanager.utils.JwtUtil;

import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoginController class
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */

public class LoginControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserServiceImp userServiceImp;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authenticationManager = Mockito.mock(AuthenticationManager.class);
        userServiceImp = Mockito.mock(UserServiceImp.class);
        jwtUtil = Mockito.mock(JwtUtil.class);
        userService = Mockito.mock(UserService.class);
        loginController = new LoginController(authenticationManager, userServiceImp, jwtUtil, userService);
    }

    /**
     * Test method for simulating authentication failure during login
     */
    @Test
    public void testLogin_AuthenticationFailure() {
        AuthenticationManager mockAuthenticationManager = mock(AuthenticationManager.class);
        when(mockAuthenticationManager.authenticate(any())).thenThrow(new UsernameNotFoundException("User not found"));
    }

}
