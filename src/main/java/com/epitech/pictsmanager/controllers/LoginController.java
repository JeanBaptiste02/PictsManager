package com.epitech.pictsmanager.controllers;


import com.epitech.pictsmanager.dtos.LoginRequest;
import com.epitech.pictsmanager.dtos.LoginResponse;
import com.epitech.pictsmanager.service.jwt.UserServiceImp;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceImp userServiceImp;
    private final JwtUtil jwtUtil;

@Autowired
    public LoginController(AuthenticationManager authenticationManager, UserServiceImp userServiceImp, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userServiceImp = userServiceImp;
        this.jwtUtil = jwtUtil;
}
@PostMapping("/login")
    public ResponseEntity<LoginResponse>  login(@RequestBody LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try {
            userDetails = userServiceImp.loadUserByUsername(loginRequest.getEmail());
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println(jwt);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
