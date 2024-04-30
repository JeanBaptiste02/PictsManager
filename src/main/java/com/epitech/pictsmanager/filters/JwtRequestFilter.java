package com.epitech.pictsmanager.filters;

import com.epitech.pictsmanager.service.jwt.UserServiceImp;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter   {

    private final UserServiceImp userServiceImp;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserServiceImp userServiceImp, JwtUtil jwtUtil) {
        this.userServiceImp = userServiceImp;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;



        if(authHeader !=null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userServiceImp.loadUserByUsername(username);


            if(jwtUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

      filterChain.doFilter(request,response);

    }
}
