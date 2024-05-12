package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PhotoRepositoryTest {

    @Mock
    private PhotoRepository photoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findPhotoById() {
        Photo photo = new Photo();
        photo.setId(1L);
        when(photoRepository.findPhotoById(1L)).thenReturn(photo);
        assertEquals(photo, photoRepository.findPhotoById(1L));
    }

    @Test
    void findByAlbumId() {
        Album album = new Album();
        album.setId(1L);
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByAlbumId(album)).thenReturn(photos);
        assertEquals(photos, photoRepository.findByAlbumId(album));
    }

    @Test
    void findByOwnerId_Id() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerId_Id(1L)).thenReturn(photos);
        assertEquals(photos, photoRepository.findByOwnerId_Id(1L));
    }

    @Test
    void findPhotoPathsByOwner_id() {
        List<String> photoPaths = new ArrayList<>();
        photoPaths.add("photoPath");
        when(photoRepository.findPhotoPathsByOwner_id(1L)).thenReturn(photoPaths);
        assertEquals(photoPaths, photoRepository.findPhotoPathsByOwner_id(1L));
    }

    @Test
    void findByOwnerIdAndAlbumId() {
        Album album = new Album();
        album.setId(1L);
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerIdAndAlbumId(1L, album)).thenReturn(photos);
        assertEquals(photos, photoRepository.findByOwnerIdAndAlbumId(1L, album));
    }

    @Test
    void findByOwnerIdAndVisibility() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findByOwnerIdAndVisibility(1L, true)).thenReturn(photos);
        assertEquals(photos, photoRepository.findByOwnerIdAndVisibility(1L, true));
    }

    @Test
    void findAllPublicPhotos() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.findAllPublicPhotos()).thenReturn(photos);
        assertEquals(photos, photoRepository.findAllPublicPhotos());
    }

    @Test
    void getPublicPhotosByOwnerId() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo());
        when(photoRepository.getPublicPhotosByOwnerId(1L)).thenReturn(photos);
        assertEquals(photos, photoRepository.getPublicPhotosByOwnerId(1L));
    }
}
