package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoginRequestTest {
	 	@Test
	    public void testGetEmail() {
	        String email = "shiva@gmail.com";
	        LoginRequest loginRequest = new LoginRequest();
	        loginRequest.setEmail(email);
	        
	        assertEquals(email, loginRequest.getEmail());
	    }

	    @Test
	    public void testSetEmail() {
	        LoginRequest loginRequest = new LoginRequest();
	        
	        String newEmail = "newemail@gmail.com";
	        loginRequest.setEmail(newEmail);
	        
	        assertEquals(newEmail, loginRequest.getEmail());
	    }

	    @Test
	    public void testGetPassword() {
	        String password = "password123";
	        LoginRequest loginRequest = new LoginRequest();
	        loginRequest.setPassword(password);
	        
	        assertEquals(password, loginRequest.getPassword());
	    }

	    @Test
	    public void testSetPassword() {
	        LoginRequest loginRequest = new LoginRequest();
	        
	        String newPassword = "newpassword456";
	        loginRequest.setPassword(newPassword);
	        
	        assertEquals(newPassword, loginRequest.getPassword());
	    }
}
