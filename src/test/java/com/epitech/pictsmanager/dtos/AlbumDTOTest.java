package com.epitech.pictsmanager.dtos;


import com.epitech.pictsmanager.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumDTOTest {
	@Test
    public void testConstructor() {
        AlbumDTO albumDTO = new AlbumDTO();
        assertNull(albumDTO.getId());
        assertNull(albumDTO.getTitle());
        assertNull(albumDTO.getOwner());

        albumDTO = new AlbumDTO("vishnu@gmail.com", 1L);
        assertEquals(1L, albumDTO.getId());
        assertNull(albumDTO.getTitle());
        assertNotNull(albumDTO.getOwner());
        assertEquals("vishnu@gmail.com", albumDTO.getOwner().getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        AlbumDTO albumDTO = new AlbumDTO();

        albumDTO.setId(1L);
        assertEquals(1L, albumDTO.getId());

        albumDTO.setTitle("Test Album");
        assertEquals("Test Album", albumDTO.getTitle());

        User owner = new User();
        owner.setEmail("vishnu@gmail.com");
        albumDTO.setOwner(owner);
        assertNotNull(albumDTO.getOwner());
        assertEquals("vishnu@gmail.com", albumDTO.getOwner().getEmail());
    }
}
