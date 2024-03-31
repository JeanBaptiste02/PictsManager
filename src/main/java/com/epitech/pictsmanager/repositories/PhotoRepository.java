package com.epitech.pictsmanager.repositories;

import org.springframework.stereotype.Repository;
import com.epitech.pictsmanager.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Photo findPhotoById(Long id);

    void deletePhotoById(Long id);

    Boolean existsPhotoById(Long id);

    Boolean findPhotoByPath(String path);

}
