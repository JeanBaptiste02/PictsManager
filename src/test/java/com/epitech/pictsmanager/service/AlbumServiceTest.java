package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.AlbumRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AlbumServiceTest {


    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AlbumService albumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album());
        albums.add(new Album());
        when(albumRepository.findAll()).thenReturn(albums);

        List<Album> result = albumService.getAllAlbums();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetMinAlbumIdByUserId() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        when(albumRepository.getMinAlbumIdByOwnerId(userId)).thenReturn(1L);

        Long result = albumService.getMinAlbumIdByUserId(userId);
        assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    public void testGetAlbumById() {
        Long albumId = 1L;
        Album album = new Album();
        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));

        Album result = albumService.getAlbumById(albumId);
        assertNotNull(result);
        assertEquals(album, result);
    }

    @Test
    public void testGetAlbumsByUserId() {
        Long userId = 1L;
        List<Album> albums = new ArrayList<>();
        albums.add(new Album());
        albums.add(new Album());
        when(albumRepository.findAlbumsByOwnerId(userId)).thenReturn(albums);

        // Test
        List<Album> result = albumService.getAlbumsByUserId(userId);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    public Album createAlbum(AlbumDTO albumDTO) {
        User owner = albumDTO.getOwner();
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null in AlbumDTO");
        }
        
        User user = userRepository.findUserByEmail(owner.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found with email: " + owner.getEmail());
        }
        
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setOwner(user);
        return albumRepository.save(album);
    }


    @Test
    public void testDeleteAlbumById() {
        Long albumId = 1L;
        Album album = new Album();
        when(albumRepository.findAlbumById(albumId)).thenReturn(album);

        assertDoesNotThrow(() -> albumService.deleteAlbumById(albumId));
    }
}
