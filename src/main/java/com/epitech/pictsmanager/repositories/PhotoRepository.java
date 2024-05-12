package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.entity.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.Photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing photo entities
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    /**
     * Retrieves a photo by its ID
     * @param id The ID of the photo to retrieve
     * @return The photo entity if found, otherwise null
     */
    Photo findPhotoById(Long id);

    /**
     * Retrieves a list of photos belonging to a specific album
     * @param albumId The ID of the album
     * @return A list of photos belonging to the specified album
     */
    List<Photo> findByAlbumId(Album albumId);

    /**
     * Retrieves a list of photos owned by a specific user
     * @param userId The ID of the user
     * @return A list of photos owned by the specified user
     */
    List<Photo> findByOwnerId_Id(Long userId);

    /**
     * Retrieves the paths of photos owned by a specific user
     * @param userId The ID of the user
     * @return A list of paths to photos owned by the specified user
     */
    List<String> findPhotoPathsByOwner_id(long userId);

    /**
     * Retrieves a list of photos owned by a specific user and belonging to a specific album
     * @param ownerId The ID of the owner user
     * @param albumId The ID of the album
     * @return A list of photos owned by the specified user and belonging to the specified album
     */
    List<Photo> findByOwnerIdAndAlbumId(Long ownerId, Album albumId);

    /**
     * Retrieves a list of photos owned by a specific user with a given visibility status
     * @param userId The ID of the owner user
     * @param visibility The visibility status
     * @return A list of photos owned by the specified user with the specified visibility status
     */
    List<Photo> findByOwnerIdAndVisibility(Long userId, boolean visibility);

    /**
     * Retrieves a list of all public photos
     * @return A list of all public photos
     */
    @Query("SELECT p FROM Photo p WHERE p.visibility = true")
    List<Photo> findAllPublicPhotos();

    /**
     * Retieves an image by ownerId
     * @param userId L'ID de l'utilisateur
     * @return La liste des photos publiques de l'utilisateur
     */
    List<Photo> getPublicPhotosByOwnerId(Long userId);
    
    /**
     * Retieves the last image of a specific album
     * @param albumId
     * @return
     */
    List<Photo> findByAlbumIdOrderByDateDesc(Album album);


}