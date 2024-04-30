package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.UserDTO;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
            String userId = jwtUtil.extractUsername(token);
            User user = userService.getUserByEmail(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        User existing_user = userService.getUserById(id);
        if(existing_user == null){
            return ResponseEntity.noContent().build();
        }else{
            existing_user.setNom(userDTO.getNom());
            existing_user.setEmail(userDTO.getEmail());
            existing_user.setPassword(userDTO.getPassword());
            saveUser(existing_user);
            return ResponseEntity.ok(existing_user);
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
