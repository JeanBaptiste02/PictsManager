package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    private AlbumController albumController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        albumController = new AlbumController(albumService);
    }

    @Test
    public void testCreateAlbum() {
        AlbumDTO albumDTO = new AlbumDTO();
        Album createdAlbum = new Album();
        when(albumService.createAlbum(albumDTO)).thenReturn(createdAlbum);

        ResponseEntity<Album> response = albumController.createAlbum(albumDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdAlbum, response.getBody());
    }

    @Test
    public void testGetAllAlbums() {
        List<Album> albums = Arrays.asList(new Album(), new Album());
        when(albumService.getAllAlbums()).thenReturn(albums);

        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(albums, response.getBody());
    }

    @Test
    public void testGetMinAlbumIdByUserId() {
        Long userId = 123L;
        Long minAlbumId = 456L;
        when(albumService.getMinAlbumIdByUserId(userId)).thenReturn(minAlbumId);

        ResponseEntity<Long> response = albumController.getMinAlbumIdByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(minAlbumId, response.getBody());
    }

    @Test
    public void testGetAlbumById() {
        Long albumId = 789L;
        Album album = new Album();
        when(albumService.getAlbumById(albumId)).thenReturn(album);

        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(album, response.getBody());
    }

    @Test
    public void testGetAlbumsByUserId() {
        Long userId = 123L;
        List<Album> albums = Arrays.asList(new Album(), new Album());
        when(albumService.getAlbumsByUserId(userId)).thenReturn(albums);

        ResponseEntity<List<Album>> response = albumController.getAlbumsByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(albums, response.getBody());
    }

    @Test
    public void testDeleteAlbumById() {
        Long albumId = 789L;
        albumController.deleteAlbumById(albumId);

        verify(albumService, times(1)).deleteAlbumById(albumId);
    }
}
