package com.epitech.pictsmanager.form;

import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.form.PhotoForm;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PhotoForm
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
class PhotoFormTest {

	/**
     * Tests the builder method of PhotoForm
     */
    @Test
    void photoFormBuilder() {
        Long id = 1L;
        String name = "Test Photo";
        String path = "/photos/test.jpg";
        String description = "This is a test photo";
        LocalDateTime date = LocalDateTime.now();
        Long albumId = 1L;
        User ownerId = new User();
        Boolean visibility = true;

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

        assertEquals(id, photoForm.getId());
        assertEquals(name, photoForm.getName());
        assertEquals(path, photoForm.getPath());
        assertEquals(description, photoForm.getDescription());
        assertEquals(date, photoForm.getDate());
        assertEquals(albumId, photoForm.getAlbum_id());
        assertEquals(ownerId, photoForm.getOwner_id());
        assertEquals(visibility, photoForm.getVisibility());
    }

    /**
     * Tests the getters and setters of PhotoForm
     */
    @Test
    void photoFormGetterSetter() {
        PhotoForm photoForm = new PhotoForm();
        Long id = 1L;
        String name = "Test Photo";
        String path = "/photos/test.jpg";
        String description = "This is a test photo";
        LocalDateTime date = LocalDateTime.now();
        Long albumId = 1L;
        User ownerId = new User();
        Boolean visibility = true;

        photoForm.setId(id);
        photoForm.setName(name);
        photoForm.setPath(path);
        photoForm.setDescription(description);
        photoForm.setDate(date);
        photoForm.setAlbum_id(albumId);
        photoForm.setOwner_id(ownerId);
        photoForm.setVisibility(visibility);

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
