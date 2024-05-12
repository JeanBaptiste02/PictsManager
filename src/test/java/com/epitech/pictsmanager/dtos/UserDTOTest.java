package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epitech.pictsmanager.entity.User;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for UserDTO
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserDTOTest {

	/**
     * Tests the constructor of UserDTO
     */
    @Test
    public void testUserDTOConstructor() {
        User user = mock(User.class);
        when(user.getNom()).thenReturn("Viky");
        when(user.getEmail()).thenReturn("viky@example.com");
        when(user.getPassword()).thenReturn("password");

        UserDTO userDTO = new UserDTO(user);

        assertEquals("Viky", userDTO.getNom());
        assertEquals("viky@example.com", userDTO.getEmail());
        assertEquals("password", userDTO.getPassword());
    }
    
    /**
     * Tests the getNom() method of UserDTO
     */
    @Test
    public void testGetNom() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNom("Jhansi");

        assertEquals("Jhansi", userDTO.getNom());
    }
    
    /**
     * Tests the getEmail() method of UserDTO
     */
    @Test
    public void testGetEmail() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("dhoni@example.com");

        assertEquals("dhoni@example.com", userDTO.getEmail());
    }

    /**
     * Tests the getPassword() method of UserDTO
     */
    @Test
    public void testGetPassword() {
        // Mock data
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password123");

        assertEquals("password123", userDTO.getPassword());
    }
}