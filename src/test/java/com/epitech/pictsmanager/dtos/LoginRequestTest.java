package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for the LoginRequest class
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */

public class LoginRequestTest {
	
		/**
		 * Test case for getting the email from LoginRequest
		 * Validates that the getEmail() method returns the correct email.
		 */
	 	@Test
	    public void testGetEmail() {
	        String email = "shiva@gmail.com";
	        LoginRequest loginRequest = new LoginRequest();
	        loginRequest.setEmail(email);
	        
	        assertEquals(email, loginRequest.getEmail());
	    }
	 	
	 	/**
	 	 * Test case for setting the email in LoginRequest
	 	 * Validates that the setEmail() method correctly sets the email.
	 	 */
	    @Test
	    public void testSetEmail() {
	        LoginRequest loginRequest = new LoginRequest();
	        
	        String newEmail = "newemail@gmail.com";
	        loginRequest.setEmail(newEmail);
	        
	        assertEquals(newEmail, loginRequest.getEmail());
	    }

	    /**
	     * Test case for getting the password from LoginRequest
	     * Validates that the getPassword() method returns the correct password.
	     */
	    @Test
	    public void testGetPassword() {
	        String password = "password123";
	        LoginRequest loginRequest = new LoginRequest();
	        loginRequest.setPassword(password);
	        
	        assertEquals(password, loginRequest.getPassword());
	    }

	    /**
	     * Test case for setting the password in LoginRequest
	     * Validates that the setPassword() method correctly sets the password.
	     */
	    @Test
	    public void testSetPassword() {
	        LoginRequest loginRequest = new LoginRequest();
	        
	        String newPassword = "newpassword456";
	        loginRequest.setPassword(newPassword);
	        
	        assertEquals(newPassword, loginRequest.getPassword());
	    }
}
