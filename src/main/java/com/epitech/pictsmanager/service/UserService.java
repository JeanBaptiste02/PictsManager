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
        // Vérifiez si l'utilisateur existe dans la base de données
        if (userRepository.existsById(user.getId())) {
            // Mettez à jour les informations de l'utilisateur
            // Assurez-vous de gérer correctement les différents cas de mise à jour (nom, email, mot de passe, etc.)
            // Par exemple :
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setNom(user.getNom());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                // Enregistrez les modifications dans la base de données
                userRepository.save(existingUser);
            }
        } else {
            // L'utilisateur n'existe pas dans la base de données, vous pouvez choisir de lever une exception ou de ne rien faire
            throw new EntityNotFoundException("User with ID " + user.getId() + " not found");
        }
    }



}
