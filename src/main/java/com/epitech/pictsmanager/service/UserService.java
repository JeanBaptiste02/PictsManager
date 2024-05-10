package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epitech.pictsmanager.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service class for managing user-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(){

    }

    /**
     * Retrieves all users from the database
     *
     * @return A list of all users in the database
     */
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID
     * @throws EntityNotFoundException if no user with the specified ID exists
     */
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    /**
     * Saves a new user or updates an existing user in the database
     * @param user The user to save or update
     * @return The saved or updated user
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their email address
     * @param email The email address of the user to retrieve
     * @return The user with the specified email address
     */
    public User getUserByEmail(String email){return userRepository.findUserByEmail(email);}

    /**
     * Checks if a user with the given email address already exists in the database
     * @param email The email address to check
     * @return true if a user with the given email address already exists, false otherwise
     */
    public Boolean isEmailAlreadyExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Updates an existing user in the database
     * @param user The updated user object
     * @throws EntityNotFoundException if no user with the specified ID exists
     */
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
