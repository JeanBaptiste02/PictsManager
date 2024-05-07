package com.epitech.pictsmanager.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.Photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Photo findPhotoById(Long id);

    void deletePhotoById(Long id);

    Boolean existsPhotoById(Long id);

    Boolean findPhotoByPath(String path);

    List<Photo> findByOwner_Id(Long userId);

    List<Photo> findByOwnerId_Id(Long userId);

    List<String> findPhotoPathsByOwner_id(long userId);

    @Query("SELECT p FROM Photo p WHERE p.owner.id = :ownerId AND p.album_id = :albumId")
    List<Photo> findByOwner_IdAndAlbum_Id(Long ownerId, Long albumId);

    List<Photo> findByOwnerIdAndVisibility(Long userId, boolean visibility);

    @Query("SELECT p FROM Photo p WHERE p.visibility = true")
    List<Photo> findAllPublicPhotos();

    Photo findUserById(Long id);
}