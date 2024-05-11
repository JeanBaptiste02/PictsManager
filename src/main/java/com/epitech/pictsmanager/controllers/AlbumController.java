package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling album-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@RestController
@RequestMapping("/api/album")
public class AlbumController {

    /**
     * Service for album-related operations
     */
    @Autowired
    private AlbumService albumService;

    /**
     * Creates a new album
     * @param albumDTO The DTO (Data Transfer Object) containing album details
     * @return ResponseEntity with the created album and HTTP status 201 (CREATED)
     */
    @PostMapping("/create")
    public ResponseEntity<Album> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album createdAlbum = albumService.createAlbum(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

    /**
     * Retrieves all albums
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    /**
     * Retrieves an album by its ID
     * @param albumId The ID of the album to retrieve
     * @return ResponseEntity with the requested album and HTTP status 200 (OK)
     */
    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        return ResponseEntity.ok(album);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Album>> getAlbumsByUserId(@PathVariable Long userId) {
        List<Album> albums = albumService.getAlbumsByUserId(userId);
        return ResponseEntity.ok(albums);
    }

    /**
     * Deletes an album by its ID
     * @param albumId The ID of the album to delete
     * @return ResponseEntity with a success message and HTTP status 200 (OK)
     */
    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long albumId) {
        albumService.deleteAlbumById(albumId);
        return ResponseEntity.ok("Album deleted successfully");
    }
}
