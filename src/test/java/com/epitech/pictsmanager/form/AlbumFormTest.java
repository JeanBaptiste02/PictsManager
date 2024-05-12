package com.epitech.pictsmanager.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AlbumForm
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class AlbumFormTest {

	/**
     * Tests the constructor of AlbumForm
     */
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

    /**
     * Tests the getters and setters of AlbumForm
     */
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

    /**
     * Tests the toString method of AlbumForm
     */
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
