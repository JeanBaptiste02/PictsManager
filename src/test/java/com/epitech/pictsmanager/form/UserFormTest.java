package com.epitech.pictsmanager.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserFormTest {

    @Test
    void testConstructor() {
        String nom = "Test User";
        String email = "test@example.com";
        String password = "testpassword";

        UserForm userForm = new UserForm(nom, email, password);

        assertEquals(nom, userForm.getNom());
        assertEquals(email, userForm.getEmail());
        assertEquals(password, userForm.getPassword());
    }

    @Test
    void testGetterAndSetter() {
        UserForm userForm = new UserForm();

        String nom = "Test User";
        String email = "test@example.com";
        String password = "testpassword";

        userForm.setNom(nom);
        userForm.setEmail(email);
        userForm.setPassword(password);

        assertEquals(nom, userForm.getNom());
        assertEquals(email, userForm.getEmail());
        assertEquals(password, userForm.getPassword());
    }

    @Test
    void testToString() {
        String nom = "Test User";
        String email = "test@example.com";
        String password = "testpassword";

        UserForm userForm = new UserForm(nom, email, password);

        String expectedToString = "UserForm(nom=Test User, email=test@example.com, password=testpassword)";
        assertEquals(expectedToString, userForm.toString());
    }
}
