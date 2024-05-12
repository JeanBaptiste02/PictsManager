package com.epitech.pictsmanager.entity;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhotoTest {
	
	 @Test
	    public void testConstructorAndGetters() {
	        // Arrange
	        Long id = 1L;
	        String name = "Test Photo";
	        String path = "/photos/test.jpg";
	        String description = "This is a test photo";
	        LocalDateTime date = LocalDateTime.now();
	        Album album = new Album();
	        User owner = new User();
	        Boolean visibility = false;

	        // Act
	        Photo photo = new Photo(name, path, description, date, album, owner);
	        photo.setId(id);

	        // Assert
	        Assertions.assertEquals(id, photo.getId());
	        Assertions.assertEquals(name, photo.getName());
	        Assertions.assertEquals(path, photo.getPath());
	        Assertions.assertEquals(description, photo.getDescription());
	        Assertions.assertEquals(date, photo.getDate());
	        Assertions.assertEquals(album, photo.getAlbum_id());
	        Assertions.assertEquals(owner, photo.getOwner());
	        Assertions.assertEquals(visibility, photo.getVisibility());
	    }

	    @Test
	    public void testSetters() {
	        // Arrange
	        Photo photo = new Photo();
	        Album album = new Album();
	        User owner = new User();

	        // Act
	        photo.setId(1L);
	        photo.setName("Test Photo");
	        photo.setPath("/photos/test.jpg");
	        photo.setDescription("This is a test photo");
	        photo.setDate(LocalDateTime.now());
	        photo.setAlbum_id(album);
	        photo.setOwner(owner);
	        photo.setVisibility(true);

	        // Assert
	        Assertions.assertEquals(1L, photo.getId());
	        Assertions.assertEquals("Test Photo", photo.getName());
	        Assertions.assertEquals("/photos/test.jpg", photo.getPath());
	        Assertions.assertEquals("This is a test photo", photo.getDescription());
	        Assertions.assertNotNull(photo.getDate());
	        Assertions.assertEquals(album, photo.getAlbum_id());
	        Assertions.assertEquals(owner, photo.getOwner());
	        Assertions.assertTrue(photo.getVisibility());
	    }

}
