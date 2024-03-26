package com.epitech.pictsmanager.controllers;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class AuthController {
    @GetMapping("sayhello")
    public String sayHello(){
        return "Hello";
    }
}
