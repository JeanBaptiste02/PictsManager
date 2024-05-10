package com.epitech.pictsmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class representing a user in the application
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs a User object with specified name, email, and password
     *
     * @param nom The name of the user
     * @param email The email of the user
     * @param password The password of the user
     */
    public User(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs a User object with specified id, name, and email
     * @param id    The ID of the user
     * @param nom   The name of the user
     * @param email The email of the user
     */
    public User(long id ,String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;

    }

    // getters and setters

    /**
     * Retrieves the ID of the user
     * @return The ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user
     * @param id The ID to set for the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the user
     * @return The name of the user
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the name of the user
     * @param nom The name to set for the user
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retrieves the email of the user
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     * @param email The email to set for the user
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
     * @param password The password to set for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
