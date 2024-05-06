package com.epitech.pictsmanager.filters;

import com.epitech.pictsmanager.entity.User;
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

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                User user = jwtUtil.extractUser(token);
                if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userServiceImp.loadUserByUsername(user.getEmail());

                    if (jwtUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                // Gérer les exceptions lors de la validation du token (par exemple, erreur de signature, token expiré)
                logger.error("Erreur lors de la validation du token JWT", e);
                // Renvoyer une réponse d'erreur appropriée (par exemple, 401 Non autorisé)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT invalide");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
