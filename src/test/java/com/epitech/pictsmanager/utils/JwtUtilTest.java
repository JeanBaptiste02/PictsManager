package com.epitech.pictsmanager.utils;

import com.epitech.pictsmanager.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtUtil jwtUtil;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExtractUser() {
        String token = generateToken();
        User user = jwtUtil.extractUser(token);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Rao ji", user.getNom());
        assertEquals("rao@gmail.com", user.getEmail());
    }

    @Test
    void testExtractExpiration() {
        String token = generateToken();
        Date expiration = jwtUtil.extractExpiration(token);

        assertNotNull(expiration);
    }

    @Test
    void testValidateToken() {
        String token = generateToken();

        when(userDetails.getUsername()).thenReturn("rao@gmail.com");
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testGenerateToken() {
        User user = new User(1L, "Rao ji", "rao@gmail.com");
        String token = jwtUtil.generateToken(user);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    private String generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1L);
        claims.put("nom", "Rao ji");
        claims.put("email", "rao@gmail.com");
        return jwtUtil.createToken(claims);
    }
}
