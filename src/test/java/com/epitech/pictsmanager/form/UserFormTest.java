package com.epitech.pictsmanager.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserForm
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class UserFormTest {

	/**
     * Tests the constructor of UserForm
     */
    @Test
    void testConstructor() {
        String nom = "";
        String email = "dhoni@gmail.com";
        String password = "testpassword";

        UserForm userForm = new UserForm(nom, email, password);

        assertEquals(nom, userForm.getNom());
        assertEquals(email, userForm.getEmail());
        assertEquals(password, userForm.getPassword());
    }

    /**
     * Tests the getters and setters of UserForm
     */
    @Test
    void testGetterAndSetter() {
        UserForm userForm = new UserForm();

        String nom = "Ms Dhoni";
        String email = "dhoni@gmail.com";
        String password = "testpassword";

        userForm.setNom(nom);
        userForm.setEmail(email);
        userForm.setPassword(password);

        assertEquals(nom, userForm.getNom());
        assertEquals(email, userForm.getEmail());
        assertEquals(password, userForm.getPassword());
    }

    /**
     * Tests the toString method of UserForm
     */
    @Test
    void testToString() {
        String nom = "Ms Dhoni";
        String email = "dhoni@gmail.com";
        String password = "testpassword";

        UserForm userForm = new UserForm(nom, email, password);

        String expectedToString = "UserForm(nom=Ms Dhoni, email=dhoni@gmail.com, password=testpassword)";
        assertEquals(expectedToString, userForm.toString());
    }
}
