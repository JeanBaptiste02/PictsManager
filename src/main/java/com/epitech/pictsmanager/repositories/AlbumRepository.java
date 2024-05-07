package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findAll();

    Album findAlbumById(Long id);

    Album getAlbumById(Long albumId);

    void deleteAlbumById(Long albumId);
}
