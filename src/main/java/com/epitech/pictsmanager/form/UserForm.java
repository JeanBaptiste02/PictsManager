package com.epitech.pictsmanager.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
