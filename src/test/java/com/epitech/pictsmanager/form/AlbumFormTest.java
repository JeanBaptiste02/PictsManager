package com.epitech.pictsmanager.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlbumFormTest {

    @Test
    void testConstructor() {
        Long id = 1L;
        String title = "Test Album";
        Long ownerId = 10L;

        AlbumForm albumForm = new AlbumForm(id, title, ownerId);

        assertEquals(id, albumForm.getId());
        assertEquals(title, albumForm.getTitle());
        assertEquals(ownerId, albumForm.getOwnerId());
    }

    @Test
    void testGetterAndSetter() {
        AlbumForm albumForm = new AlbumForm();

        Long id = 1L;
        String title = "Test Album";
        Long ownerId = 10L;

        albumForm.setId(id);
        albumForm.setTitle(title);
        albumForm.setOwnerId(ownerId);

        assertEquals(id, albumForm.getId());
        assertEquals(title, albumForm.getTitle());
        assertEquals(ownerId, albumForm.getOwnerId());
    }

    @Test
    void testToString() {
        Long id = 1L;
        String title = "Test Album";
        Long ownerId = 10L;

        AlbumForm albumForm = new AlbumForm(id, title, ownerId);

        String expectedToString = "AlbumForm(id=1, title=Test Album, ownerId=10)";
        assertEquals(expectedToString, albumForm.toString());
    }
}
