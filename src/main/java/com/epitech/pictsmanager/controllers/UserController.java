package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("sayhello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("getusers")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
