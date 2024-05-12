package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PhotoControllerTest {

    @Mock
    private PhotoService photoService;

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private PhotoController photoController;

    private MockHttpServletRequest request;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
    }

    @Test
    public void testGetPhotosByAlbumId() {
        Long ownerId = 1L;
        Album albumId = new Album();
        albumId.setId(1L);
        List<Photo> photosList = new ArrayList<>();
        when(photoService.getPhotosByOwnerIdAndAlbumId(ownerId, albumId)).thenReturn(photosList);

        ResponseEntity<List<Photo>> response = photoController.getPhotosByAlbumId(ownerId, albumId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(photosList, response.getBody());
    }

    @Test
    public void testGetPhotosByUserId() {
        Long userId = 1L;
        List<Photo> photoListByUser = new ArrayList<>();
        when(photoService.getPhotosByOwnerId(userId)).thenReturn(photoListByUser);

        ResponseEntity<List<Photo>> response = photoController.getPhotosByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(photoListByUser, response.getBody());
    }

    @Test
    public void testGetPhotosByUserIdAndVisibility() {
        Long userId = 1L;
        boolean visibility = true;
        List<Photo> photos = new ArrayList<>();
        when(photoService.getPhotosByUserIdAndVisibility(userId, visibility)).thenReturn(photos);

        ResponseEntity<List<Photo>> response = photoController.getPhotosByUserIdAndVisibility(userId, visibility);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(photos, response.getBody());
    }

    @Test
    public void testGetAllPublicPhotos() {
        List<Photo> publicPhotos = new ArrayList<>();
        when(photoService.getAllPublicPhotos()).thenReturn(publicPhotos);

        ResponseEntity<List<Photo>> response = photoController.getAllPublicPhotos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(publicPhotos, response.getBody());
    }

    @Test
    public void testGetPhotosByAlbumId_AlbumIdOnly() {
        Album albumId = new Album();
        albumId.setId(1L);
        List<Photo> photos = new ArrayList<>();
        when(photoService.getPhotosByAlbumId(albumId)).thenReturn(photos);

        ResponseEntity<List<Photo>> response = photoController.getPhotosByAlbumId(albumId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(photos, response.getBody());
    }

    @Test
    public void testGetPublicPhotosByUserId() {
        Long userId = 1L;
        List<Photo> publicPhotosByUser = new ArrayList<>();
        when(photoService.getPublicPhotosByOwnerId(userId)).thenReturn(publicPhotosByUser);

        ResponseEntity<List<Photo>> response = photoController.getPublicPhotosByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(publicPhotosByUser, response.getBody());
    }

    @Test
    public void testDeletePhoto_Success() throws IOException {
        Long photoId = 1L;
        User existingUser = new User();
        existingUser.setId(1L);
        Photo photo = new Photo();
        photo.setId(photoId);
        photo.setOwner(existingUser);
        when(jwtUtil.extractUser(anyString())).thenReturn(existingUser);
        when(userService.getUserById(anyLong())).thenReturn(existingUser);
        when(photoService.getPhotoById(photoId)).thenReturn(photo);
        doNothing().when(photoService).deletePhotosById(photoId);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/photo/delete/{photoId}", photoId);
        MockHttpServletRequest request = requestBuilder.buildRequest(new MockServletContext());
        request.addHeader("Authorization", "Bearer validToken");

        ResponseEntity<String> response = photoController.delete(photoId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Photo deleted successfully", response.getBody());
    }



    @Test
    public void testDeletePhoto_Unauthorized() throws Exception {
        Long photoId = 1L;
        User existingUser = new User();
        existingUser.setId(1L);
        Photo photo = new Photo();
        photo.setId(photoId);
        photo.setOwner(existingUser);
        when(userService.getUserById(anyLong())).thenReturn(existingUser);
        when(photoService.getPhotoById(photoId)).thenReturn(photo);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/photo/delete/{photoId}", photoId);
        MockHttpServletRequest request = requestBuilder.buildRequest(new MockServletContext());

        ResponseEntity<String> response = photoController.delete(photoId, request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testUploadPhoto_Success() throws IOException {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setNom("testUser");
        Album album = new Album();
        album.setId(1L);
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[0]); // Utilisation d'un tableau de bytes vide pour simuler le fichier
        String name = "testPhoto";
        String description = "testDescription";
        when(userService.getUserById(anyLong())).thenReturn(existingUser);
        when(jwtUtil.extractUser(anyString())).thenReturn(existingUser);
        request.addHeader("Authorization", "Bearer validToken");
    }

    @Test
    public void testUploadPhoto_Unauthorized() throws IOException {
        User existingUser = mock(User.class);
        Album album = mock(Album.class);
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        MockServletContext context = new MockServletContext();

        when(userService.getUserById(anyLong())).thenReturn(existingUser);

        when(jwtUtil.extractUser(anyString())).thenReturn(existingUser);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/api/photo/upload")
                .file(file)
                .param("name", "testPhoto")
                .param("description", "testDescription");
        
        requestBuilder.header("Authorization", "Bearer invalidToken");

     
    }
}