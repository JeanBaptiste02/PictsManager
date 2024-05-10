package com.epitech.pictsmanager.dtos;

/**
 * DTO class representing a login response
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class LoginResponse {

    private String jwtToken;

    /**
     * Constructs a new LoginResponse with the provided JWT token
     * @param jwtToken The JWT token generated upon successful login
     */
    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    /**
     * Retrieves the JWT token
     * @return The JWT token
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets the JWT token
     * @param jwtToken The JWT token to set
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
