package com.epitech.pictsmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;


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

    @Column(name = "album_id")
    private Long album_id;

    @Column(name = "owner_id")
    private User owner_id;

    @Lob
    @Column(name = "image")
    private Blob image;

    public Photo() {

    }

    public Photo(String name, String path, String description, LocalDateTime date, Long album_id, User owner_id, Blob image){
        this.name = name;
        this.path = path;
        this.description = description;
        this.date = date;
        this.album_id = album_id;
        this.owner_id = owner_id;
        this.image = image;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }


}
