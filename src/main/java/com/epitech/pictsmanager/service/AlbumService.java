package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.repositories.AlbumRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with ID: " + albumId));
    }

    public Album createAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setTitle(albumDTO.getTitle());
        return albumRepository.save(album);
    }

    public void deleteAlbumById(Long albumId) {
        Album album = albumRepository.findAlbumById(albumId);
        if(album != null) {
            albumRepository.delete(album);
        }else{
            throw new RuntimeException("Album not found with ID: " + albumId);
        }

    }

}
