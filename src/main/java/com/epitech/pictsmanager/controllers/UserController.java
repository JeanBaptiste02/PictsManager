package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
         @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("getusers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("adduser")
    public User saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }
}
