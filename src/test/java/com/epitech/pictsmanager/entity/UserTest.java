package com.epitech.pictsmanager.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Test
    public void testUserConstructor() {
        String nom = "Rohit Sharma";
        String email = "rohit.sharma@example.com";
        String password = "password123";

        User user = new User(nom, email, password);

        Assertions.assertEquals(nom, user.getNom());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        Long id = 1L;
        String nom = "Suryakumar Yadav";
        String email = "suryakumar.yadav@example.com";
        String password = "password456";

        user.setId(id);
        user.setNom(nom);
        user.setEmail(email);
        user.setPassword(password);

        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(nom, user.getNom());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
    }
}
