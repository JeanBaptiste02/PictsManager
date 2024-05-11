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
        album.setId(albumDTO.getId());
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

}
