package com.epitech.pictsmanager.repositories;

import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing user data in the database
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Retrieves a user by their ID
     * @param id The ID of the user
     * @return The user with the specified ID, or null if not found
     */
    User findUserById(Long id);

    /**
     * Retrieves a user by their email address
     * @param email The email address of the user
     * @return The user with the specified email address, or null if not found
     */
    User findUserByEmail(String email);

    /**
     * Retrieves a user by their email address
     * @param email The email address of the user
     * @return An Optional containing the user with the specified email address, or an empty Optional if not found
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Retrieves a user by their name
     * @param nom The name of the user
     * @return The user with the specified name, or null if not found
     */
    User findByNom(String nom);
    
}
