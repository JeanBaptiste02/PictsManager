package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the photo service
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@SpringBootTest
class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private UserRepository userRepository;

    private PhotoService photoService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        photoService = new PhotoService();
        photoService.photoRepository = photoRepository;
        photoService.userRepository = userRepository;
    }

    /**
     * Test for getting all photos
     */
    @Test
    void getPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findAll()).thenReturn(photos);
        assertEquals(photos, photoService.getPhotos());
    }

    /**
     * Test for getting photos by owner ID and album ID
     */
    @Test
    void getPhotosByOwnerIdAndAlbumId() {
        Album album = new Album();
        album.setId(1L);
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerIdAndAlbumId(1L, album)).thenReturn(photos);
        assertEquals(photos, photoService.getPhotosByOwnerIdAndAlbumId(1L, album));
    }

    /**
     * Test for getting photos by owner ID
     */
    @Test
    void getPhotosByOwnerId() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerId_Id(1L)).thenReturn(photos);
        assertEquals(photos, photoService.getPhotosByOwnerId(1L));
    }

    /**
     * Test for finding a user by ID
     */
    @Test
    void findUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, photoService.findUserById(1L));
    }

    /**
     * Test for getting a photo by its ID
     */
    @Test
    void getPhotoById() {
        Photo photo = new Photo();
        photo.setId(1L);
        when(photoRepository.findPhotoById(1L)).thenReturn(photo);
        assertEquals(photo, photoService.getPhotoById(1L));
    }

    /**
     * Test for getting photos by album ID
     */
    @Test
    void getPhotosByAlbumId() {
        Album album = new Album();
        album.setId(1L);
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByAlbumId(album)).thenReturn(photos);
        assertEquals(photos, photoService.getPhotosByAlbumId(album));
    }

    /**
     * Test for saving a photo
     */
    @Test
    void savePhoto() {
        Photo photo = new Photo();
        photoService.savePhoto(photo);
        verify(photoRepository, times(1)).save(photo);
    }

    /**
     * Test for getting photo paths by user ID
     */
    @Test
    void getPhotoPathsByUserId() {
        List<String> photoPaths = new ArrayList<>();
        photoPaths.add("photoPath");
        when(photoRepository.findPhotoPathsByOwner_id(1L)).thenReturn(photoPaths);
        assertEquals(photoPaths, photoService.getPhotoPathsByUserId(1L));
    }

    /**
     * Test for combining images
     */
    @Test
    void combineImages() throws IOException {
        List<String> photoPaths = new ArrayList<>();
        byte[] result = photoService.combineImages(photoPaths);
        assertNotNull(result);
    }

    /**
     * Test for compressing and saving an image
     */
    @SuppressWarnings("unused")
	@Test
    void compressAndSaveImage() throws IOException {

    	PhotoRepository photoRepositoryMock = mock(PhotoRepository.class);
        @SuppressWarnings("unused")
		PhotoService photoService = new PhotoService();
        String tempDir = System.getProperty("java.io.tmpdir");
        @SuppressWarnings("unused")
		String albumIdDirPath = tempDir + File.separator + "test_album";
    }

    /**
     * Test for getting photos by user ID and visibility
     */
    @Test
    void getPhotosByUserIdAndVisibility() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerIdAndVisibility(1L, true)).thenReturn(photos);
        assertEquals(photos, photoService.getPhotosByUserIdAndVisibility(1L, true));
    }

    /**
     * Test for getting all public photos
     */
    @Test
    void getAllPublicPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findAllPublicPhotos()).thenReturn(photos);
        assertEquals(photos, photoService.getAllPublicPhotos());
    }

    /**
     * Test for deleting photos by their IDs
     */
    @Test
    void deletePhotosById() {
        Photo photo = new Photo();
        photo.setId(1L);
        photo.setPath("photoPath");
        when(photoRepository.findPhotoById(1L)).thenReturn(photo);
        photoService.deletePhotosById(1L);
        verify(photoRepository, times(1)).delete(photo);
    }

    /**
     * Test for getting public photos by owner ID
     */
    @Test
    void getPublicPhotosByOwnerId() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerIdAndVisibility(1L, true)).thenReturn(photos);
        assertEquals(photos, photoService.getPublicPhotosByOwnerId(1L));
    }
}
