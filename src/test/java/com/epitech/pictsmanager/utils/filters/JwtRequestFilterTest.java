package com.epitech.pictsmanager.utils.filters;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.jwt.UserServiceImp;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test class for JwtRequestFilter
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class JwtRequestFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserServiceImp userServiceImp;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test for filtering with a valid token
     */
    @Test
    void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        String token = "valid_jwt_token";
        User user = new User(1L, "John Doe", "john@example.com");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), "", authorities);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUser(token)).thenReturn(user);
        when(userServiceImp.loadUserByUsername(user.getEmail())).thenReturn(userDetails);
        when(jwtUtil.validateToken(token, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    /**
     * Test for filtering with an invalid token
     */
    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        String token = "invalid_jwt_token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.extractUser(token)).thenThrow(new RuntimeException("Invalid token"));

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT invalide");
    }

    /**
     * Test for filtering with no Authorization header
     */
    @Test
    void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}
