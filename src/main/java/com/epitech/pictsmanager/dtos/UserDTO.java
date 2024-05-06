package com.epitech.pictsmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.epitech.pictsmanager.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String nom;
    private String email;
    private String password;

    public UserDTO(final User user){
        nom = user.getNom();
        email = user.getEmail();
        password = user.getPassword();
    }

    public String getNom(){
        return this.nom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
