package com.epitech.pictsmanager.form;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.form.PhotoForm;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PhotoFormTest {

    @Test
    void photoFormBuilder() {
        // Arrange
        Long id = 1L;
        String name = "Test Photo";
        String path = "/photos/test.jpg";
        String description = "This is a test photo";
        LocalDateTime date = LocalDateTime.now();
        Long albumId = 1L;
        User ownerId = new User();
        Boolean visibility = true;

        // Act
        PhotoForm photoForm = PhotoForm.builder()
                .id(id)
                .name(name)
                .path(path)
                .description(description)
                .date(date)
                .album_id(albumId)
                .owner_id(ownerId)
                .visibility(visibility)
                .build();

        // Assert
        assertEquals(id, photoForm.getId());
        assertEquals(name, photoForm.getName());
        assertEquals(path, photoForm.getPath());
        assertEquals(description, photoForm.getDescription());
        assertEquals(date, photoForm.getDate());
        assertEquals(albumId, photoForm.getAlbum_id());
        assertEquals(ownerId, photoForm.getOwner_id());
        assertEquals(visibility, photoForm.getVisibility());
    }

    @Test
    void photoFormGetterSetter() {
        // Arrange
        PhotoForm photoForm = new PhotoForm();
        Long id = 1L;
        String name = "Test Photo";
        String path = "/photos/test.jpg";
        String description = "This is a test photo";
        LocalDateTime date = LocalDateTime.now();
        Long albumId = 1L;
        User ownerId = new User();
        Boolean visibility = true;

        // Act
        photoForm.setId(id);
        photoForm.setName(name);
        photoForm.setPath(path);
        photoForm.setDescription(description);
        photoForm.setDate(date);
        photoForm.setAlbum_id(albumId);
        photoForm.setOwner_id(ownerId);
        photoForm.setVisibility(visibility);

        // Assert
        assertEquals(id, photoForm.getId());
        assertEquals(name, photoForm.getName());
        assertEquals(path, photoForm.getPath());
        assertEquals(description, photoForm.getDescription());
        assertEquals(date, photoForm.getDate());
        assertEquals(albumId, photoForm.getAlbum_id());
        assertEquals(ownerId, photoForm.getOwner_id());
        assertEquals(visibility, photoForm.getVisibility());
    }
}
