package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epitech.pictsmanager.entity.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(){

    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
