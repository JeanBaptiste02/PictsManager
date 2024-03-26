package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getusers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("adduser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
