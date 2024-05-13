package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.service.AlbumService;
import com.epitech.pictsmanager.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for handling album-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@RestController
@RequestMapping("/api/album")
public class AlbumController {

	@Autowired
    private JwtUtil jwtUtil;
	
    @Autowired
    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
    
    /**
     * Creates a new album
     * @param albumDTO The DTO (Data Transfer Object) containing album details
     * @return ResponseEntity with the created album and HTTP status 201 (CREATED)
     */
    @PostMapping("/create")
    public ResponseEntity<Album> createAlbum(@RequestBody AlbumDTO albumDTO) {
        if (albumDTO.getTitle() == null || albumDTO.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
     * Retrieves the minimum album ID associated with a given user ID
     * @param userId The ID of the user
     * @return ResponseEntity with the minimum album ID and HTTP status 200 (OK), or 404 (NOT_FOUND) if no album is found for the user
     */
    @GetMapping("/min-id/user/{userId}")
    public ResponseEntity<Long> getMinAlbumIdByUserId(@PathVariable Long userId) {
        try {
            Long minAlbumId = albumService.getMinAlbumIdByUserId(userId);
            return ResponseEntity.ok(minAlbumId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
    
    /**
     * Retrieves all albums associated with a specific user ID
     * @param userId The ID of the user
     * @return ResponseEntity with the list of albums associated with the user and HTTP status 200 (OK)
     */
    @GetMapping("/user")
    public ResponseEntity<List<Album>> getAlbumsByUserId(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        if (token != null) {
            Long userId = jwtUtil.extractUser(token).getId();
            List<Album> albums = albumService.getAlbumsByUserId(userId);
            return ResponseEntity.ok(albums);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * Extracts the JWT token from the HTTP servlet request
     * @param request The HTTP servlet request
     * @return The extracted JWT token, or null if not found
     */
    public String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
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
