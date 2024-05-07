package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/create")
    public ResponseEntity<Album> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album createdAlbum = albumService.createAlbum(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long albumId) {
        albumService.deleteAlbumById(albumId);
        return ResponseEntity.ok("Album deleted successfully");
    }
}
