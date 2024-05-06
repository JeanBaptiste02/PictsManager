package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoService photoService;

    public void deleteAlbum(Long albumId, Long userId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        if (!album.getOwner().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to delete this album");
        }

        photoService.deletePhotosByAlbumId(albumId);
        albumRepository.deleteById(albumId);
    }
}
