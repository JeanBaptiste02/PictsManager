package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.AlbumDTO;
import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.AlbumService;
import com.epitech.pictsmanager.utils.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AlbumController class
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */

class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    private AlbumController albumController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        albumController = new AlbumController(albumService);
    }

    /**
     * Test method for creating an album
     */
    @Test
    public void testCreateAlbum() {
        AlbumDTO albumDTO = new AlbumDTO();
        Album createdAlbum = new Album();
        when(albumService.createAlbum(albumDTO)).thenReturn(createdAlbum);

        ResponseEntity<Album> response = albumController.createAlbum(albumDTO);
    }

    /**
     * Test method for retrieving all albums
     */
    @Test
    public void testGetAllAlbums() {
        List<Album> albums = Arrays.asList(new Album(), new Album());
        when(albumService.getAllAlbums()).thenReturn(albums);

        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(albums, response.getBody());
    }

    /**
     * Test method for retrieving the minimum album ID by user ID
     */
    @Test
    public void testGetMinAlbumIdByUserId() {
        Long userId = 123L;
        Long minAlbumId = 456L;
        when(albumService.getMinAlbumIdByUserId(userId)).thenReturn(minAlbumId);

        ResponseEntity<Long> response = albumController.getMinAlbumIdByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(minAlbumId, response.getBody());
    }

    /**
     * Test method for retrieving an album by its ID
     */
    @Test
    public void testGetAlbumById() {
        Long albumId = 789L;
        Album album = new Album();
        when(albumService.getAlbumById(albumId)).thenReturn(album);

        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(album, response.getBody());
    }

    /**
     * Test method for retrieving albums by user ID
     */
    @Test
    public void testGetAlbumsByUserId() {
        Long userId = 123L;
        List<Album> albums = Arrays.asList(new Album(), new Album());
        when(albumService.getAlbumsByUserId(userId)).thenReturn(albums);

        JwtUtil jwtUtil = Mockito.mock(JwtUtil.class);
        User user = new User();
        user.setId(userId);
        when(jwtUtil.extractUser(Mockito.anyString())).thenReturn(user);

        ReflectionTestUtils.setField(albumController, "jwtUtil", jwtUtil);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        String token = "your_valid_token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        ResponseEntity<List<Album>> response = albumController.getAlbumsByUserId(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(albums, response.getBody());
    }


    /**
     * Test method for deleting an album by its ID
     */
    @Test
    public void testDeleteAlbumById() {
        Long albumId = 789L;
        albumController.deleteAlbumById(albumId);

        verify(albumService, times(1)).deleteAlbumById(albumId);
    }
    
    /**
     * Test method for token extraction - valid token
     */
    @Test
    public void testExtractTokenFromRequest_ValidToken() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        String token = "valid_token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        String extractedToken = albumController.extractTokenFromRequest(request);

        assertEquals(token, extractedToken);
    }

    /**
     * Test method for token extraction - without authentification header
     */
    @Test
    public void testExtractTokenFromRequest_NoAuthorizationHeader() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn(null);

        String extractedToken = albumController.extractTokenFromRequest(request);

        assertNull(extractedToken);
    }

    /**
     * Test method for token extraction - invalid authentification header
     */
    @Test
    public void testExtractTokenFromRequest_InvalidAuthorizationHeader() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Invalid Header");

        String extractedToken = albumController.extractTokenFromRequest(request);

        assertNull(extractedToken);
    }

}
