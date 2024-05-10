package com.epitech.pictsmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.epitech.pictsmanager.entity.User;

/**
 * Data Transfer Object (DTO) class for the User entity
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String nom;
    private String email;
    private String password;

    /**
     * Constructs a UserDTO object from a User entity
     * @param user The User entity from which to construct the DTO
     */
    public UserDTO(final User user){
        nom = user.getNom();
        email = user.getEmail();
        password = user.getPassword();
    }

    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Retrieves the email of the user
     * @return The email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retrieves the password of the user
     * @return The password of the user
     */
    public String getPassword() {
        return this.password;
    }
}
