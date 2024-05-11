package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epitech.pictsmanager.entity.User;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDTOTest {

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

    @Test
    public void testGetNom() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNom("Jhansi");

        assertEquals("Jhansi", userDTO.getNom());
    }

    @Test
    public void testGetEmail() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("dhoni@example.com");

        assertEquals("dhoni@example.com", userDTO.getEmail());
    }

    @Test
    public void testGetPassword() {
        // Mock data
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password123");

        assertEquals("password123", userDTO.getPassword());
    }
}