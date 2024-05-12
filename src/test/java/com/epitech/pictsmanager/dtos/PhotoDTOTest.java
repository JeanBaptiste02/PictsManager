package com.epitech.pictsmanager.dtos;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for PhotoDTO
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class PhotoDTOTest {
	
	/**
     * Tests the constructor with a Photo entity
     */
	@Test
    public void testConstructorWithPhotoEntity() {
        Long id = 1L;
        String name = "Test Photo";
        String path = "/photos/test.jpg";
        String description = "This is a test photo";
        LocalDateTime date = LocalDateTime.now();
        Album album = new Album();
        User owner = new User();
        Boolean visibility = true;

        Photo photo = new Photo();
        photo.setId(id);
        photo.setName(name);
        photo.setPath(path);
        photo.setDescription(description);
        photo.setDate(date);
        photo.setAlbum_id(album);
        photo.setOwner(owner);
        photo.setVisibility(visibility);

        PhotoDTO photoDTO = new PhotoDTO(photo);

        Assertions.assertEquals(id, photoDTO.getId());
        Assertions.assertEquals(name, photoDTO.getName());
        Assertions.assertEquals(path, photoDTO.getPath());
        Assertions.assertEquals(description, photoDTO.getDescription());
        Assertions.assertEquals(date, photoDTO.getDate());
        Assertions.assertEquals(album, photoDTO.getAlbum_id());
    }

	/**
     * Tests the empty constructor
     */
    @Test
    public void testEmptyConstructor() {
        PhotoDTO photoDTO = new PhotoDTO();

        Assertions.assertNull(photoDTO.getId());
        Assertions.assertNull(photoDTO.getName());
        Assertions.assertNull(photoDTO.getPath());
        Assertions.assertNull(photoDTO.getDescription());
        Assertions.assertNull(photoDTO.getDate());
        Assertions.assertNull(photoDTO.getAlbum_id());
    }

}
