package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing album entities
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * Retrieves all albums
     * @return A list of all albums
     */
    List<Album> findAll();

    /**
     * Retrieves an album by its ID.
     * @param id The ID of the album to retrieve
     * @return The album with the specified ID
     */
    Album findAlbumById(Long id);

    /**
     * Retrieves an album by its ID
     * @param albumId The ID of the album to retrieve
     * @return The album with the specified ID
     */
    Album getAlbumById(Long albumId);

    /**
     * Deletes an album by its ID
     * @param albumId The ID of the album to delete
     */
    void deleteAlbumById(Long albumId);
    
    /**
     * Retrieves all albums associated with a specific owner ID
     * @param ownerId The ID of the owner
     * @return A list of albums associated with the owner
     */
    List<Album> findAlbumsByOwnerId(Long userId);
    
    /**
     * Retrieves the minimum album ID associated with a given owner ID
     * @param ownerId The ID of the owner
     * @return The minimum album ID associated with the owner
     */
    @Query(value = "SELECT MIN(id) FROM albums WHERE owner_id = :ownerId", nativeQuery = true)
    Long getMinAlbumIdByOwnerId(@Param("ownerId") Long ownerId);
}
