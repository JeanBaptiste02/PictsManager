package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epitech.pictsmanager.entity.User;

import javax.persistence.EntityNotFoundException;
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

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserByEmail(String email){return userRepository.findUserByEmail(email);}

    public Boolean isEmailAlreadyExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setNom(user.getNom());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                userRepository.save(existingUser);
            }
        } else {
            throw new EntityNotFoundException("User with ID " + user.getId() + " not found");
        }
    }



}
