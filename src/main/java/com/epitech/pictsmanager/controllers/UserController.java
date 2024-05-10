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

/**
 * Controller class for managing user-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Retrieves all users
     * @return A list of all users
     */
    @GetMapping("getusers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * Retrieves a user by ID
     * @param request The HTTP servlet request containing the JWT token
     * @return The user entity if found, otherwise returns a no content response
     */
    @GetMapping("/getuser")
    public ResponseEntity<User> getUserById(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            User user = userService.getUserById(jwtUtil.extractUser(token).getId());
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates a user's information
     * @param user    The updated user entity
     * @param request The HTTP servlet request containing the JWT token
     * @return The updated user entity if successful, otherwise returns a not found or unauthorized response
     */
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

    /**
     * Saves a new user
     * @param user The user entity to save
     * @return A response entity indicating whether the user has been added successfully or not
     */
    @PostMapping("adduser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        if(userService.isEmailAlreadyExists(user.getEmail())){
            return ResponseEntity.badRequest().body("This email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        return ResponseEntity.ok("User has been added");
    }

    /**
     * Extracts the JWT token from the HTTP servlet request
     * @param request The HTTP servlet request
     * @return The extracted JWT token, or null if not found
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
