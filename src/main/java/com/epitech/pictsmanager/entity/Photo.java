package com.epitech.pictsmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

/**
 * Entity class representing a photo
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Entity
@Table(name = "photos")
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album albumId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "visibility")
    private Boolean visibility;

    /**
     * Default constructor
     */
    public Photo() {

    }

    /**
     * Parameterized constructor
     * @param name The name of the photo
     * @param path The path to the photo file
     * @param description The description of the photo
     * @param date The date when the photo was uploaded
     * @param albumId The album to which the photo belongs
     * @param owner The owner of the photo
     */
    public Photo(String name, String path, String description, LocalDateTime date, Album albumId, User owner){
        this.name = name;
        this.path = path;
        this.description = description;
        this.date = date;
        this.albumId = albumId;
        this.owner = owner;
        this.visibility = false;

    }

    // getters and setters

    /**
     * Retrieves the unique identifier of the photo
     * @return The ID of the photo
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the photo
     * @param id The ID to set for the photo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the photo
     * @return The name of the photo
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the photo
     * @param name The name to set for the photo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the path to the photo file
     * @return The path to the photo file
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path to the photo file
     * @param path The path to set for the photo file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Retrieves the description of the photo
     * @return The description of the photo
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the photo
     * @param description The description to set for the photo
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the date when the photo was uploaded
     * @return The upload date of the photo
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date when the photo was uploaded
     * @param date The upload date to set for the photo
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Retrieves the album to which the photo belongs
     * @return The album of the photo
     */
    public Album getAlbum_id() {
        return albumId;
    }

    /**
     * Sets the album to which the photo belongs
     * @param albumId The album to set for the photo
     */
    public void setAlbum_id(Album albumId) {
        this.albumId = albumId;
    }

    /**
     * Retrieves the owner of the photo
     * @return The owner of the photo
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the photo
     * @param owner The owner to set for the photo
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Retrieves the visibility status of the photo
     * @return The visibility status of the photo
     */
    public Boolean getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility status of the photo
     * @param visibility The visibility status to set for the photo
     */
    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

}