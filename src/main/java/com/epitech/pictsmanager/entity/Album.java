package com.epitech.pictsmanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class representing an album
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Entity
@Table(name = "albums")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    /**
     * Default constructor for Album
     */
    public Album() {

    }

    /**
     * Constructs an Album object with specified attributes
     * @param id The unique identifier of the album
     * @param title The title of the album
     * @param owner The owner of the album
     */
    public Album(Long id, String title, User owner){
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    // getters and setters

    /**
     * Retrieves the ID of the album
     * @return The ID of the album
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the album
     * @param id The ID to set for the album
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the owner of the album
     * @return The owner of the album
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the album
     * @param owner The owner to set for the album
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Retrieves the title of the album
     * @return The title of the album
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the album
     * @param title The title to set for the album
     */
    public void setTitle(String title) {
        this.title = title;
    }

}