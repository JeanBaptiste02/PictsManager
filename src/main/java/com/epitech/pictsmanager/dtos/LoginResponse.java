package com.epitech.pictsmanager.dtos;

public class LoginResponse {

    private String jwtToken;

    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
