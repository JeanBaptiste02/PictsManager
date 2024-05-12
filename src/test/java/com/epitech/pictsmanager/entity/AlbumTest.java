package com.epitech.pictsmanager.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Album entity
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class AlbumTest {
	
		/**
	     * Tests the constructor of Album
	     */
	 	@Test
	    public void testConstructor() {
	        Album album = new Album();
	        assertNull(album.getId());
	        assertNull(album.getTitle());
	        assertNull(album.getOwner());

	        User owner = new User();
	        album = new Album(1L, "Test Album", owner);
	        assertEquals(1L, album.getId());
	        assertEquals("Test Album", album.getTitle());
	        assertNotNull(album.getOwner());
	        assertEquals(owner, album.getOwner());
	    }

	 	 /**
	     * Tests the setters and getters of Album
	     */
	    @Test
	    public void testSettersAndGetters() {
	        Album album = new Album();

	        album.setId(1L);
	        assertEquals(1L, album.getId());

	        album.setTitle("Test Album");
	        assertEquals("Test Album", album.getTitle());

	        User owner = new User();
	        album.setOwner(owner);
	        assertNotNull(album.getOwner());
	        assertEquals(owner, album.getOwner());
	    }

}
