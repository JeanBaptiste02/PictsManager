package com.epitech.pictsmanager.dtos;

/**
 * DTO class representing a login request
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class LoginRequest {
    private String email;

    private String password;

    /**
     * Retrieves the email of the user
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
