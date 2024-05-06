package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.UserDTO;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("getusers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> getUserById(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            //String username = jwtUtil.extractUsername(token);
            User user = userService.getUserById(jwtUtil.extractUser(token).getId());
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/user")
    public ResponseEntity<?> updateUser(@RequestBody User user, HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            User existingUser = userService.getUserById(jwtUtil.extractUser(token).getId());
            if (existingUser != null) {
                existingUser.setNom(user.getNom());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.updateUser(existingUser);
                return ResponseEntity.ok(existingUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }


    @PostMapping("adduser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        if(userService.isEmailAlreadyExists(user.getEmail())){
            return ResponseEntity.badRequest().body("This email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        return ResponseEntity.ok("User has been added");
    }





    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
