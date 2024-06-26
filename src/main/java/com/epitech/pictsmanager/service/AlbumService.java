package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.AlbumRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing album-related operations
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all albums
     * @return A list of all albums
     */
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    
    /**
     * Retrieves the minimum album ID associated with a given user ID
     * @param userId The ID of the user
     * @return The minimum album ID associated with the user
     * @throws RuntimeException if the user does not exist or if no albums are found for the user
     */
    public Long getMinAlbumIdByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        Long minAlbumId = albumRepository.getMinAlbumIdByOwnerId(userId);

        if (minAlbumId == null) {
            throw new RuntimeException("No albums found for user with ID: " + userId);
        }

        return minAlbumId;
    }

    /**
     * Retrieves an album by its ID
     * @param albumId The album with the specified ID
     * @return The album with the specified ID
     * @throws RuntimeException if no album is found with the given ID
     */
    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with ID: " + albumId));
    }
    
    /**
     * Retrieves all albums associated with a specific user ID
     * @param userId The ID of the user
     * @return A list of albums associated with the user
     */
    public List<Album> getAlbumsByUserId(Long userId) {
        return albumRepository.findAlbumsByOwnerId(userId);
    }

    /**
     * Creates a new album
     * @param albumDTO The DTO containing album details
     * @return The created album
     */
    public Album createAlbum(AlbumDTO albumDTO) {
        User user = userRepository.findUserByEmail(albumDTO.getOwner().getEmail());
        if (user == null) {
            throw new RuntimeException("User not found with name: " + albumDTO.getOwner().getEmail());
        }
        Album album = new Album();
        album.setTitle(albumDTO.getTitle()); 
        album.setOwner(user);
        return albumRepository.save(album);
    }


    /**
     * Deletes an album by its ID
     * @param albumId The ID of the album to delete
     * @throws RuntimeException if no album is found with the given ID
     */
    public void deleteAlbumById(Long albumId) {
        Album album = albumRepository.findAlbumById(albumId);
        if(album != null) {
            albumRepository.delete(album);
        }else{
            throw new RuntimeException("Album not found with ID: " + albumId);
        }

    }

    /**
     * Updates an album's title
     * @param albumId the id of the album
     * @param newTitle The new title of the album
     * @return
     */
    public Album updateAlbumTitle(Long albumId, String newTitle) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with ID: " + albumId));

        album.setTitle(newTitle);
        return albumRepository.save(album);
    }

}
