package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests for the LoginResponse class
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */

public class LoginResponseTest {

	/**
	 * Test case for getting the JWT token from LoginResponse
	 * Validates that the getJwtToken() method returns the correct JWT token.
	 */
    @Test
    public void testGetJwtToken() {
        String jwtToken = "tokenex";
        LoginResponse loginResponse = new LoginResponse(jwtToken);
        
        assertEquals(jwtToken, loginResponse.getJwtToken());
    }


	/**
	 * Test case for setting the JWT token in LoginResponse
	 * Validates that the setJwtToken() method correctly sets the JWT token.
	 */
    @Test
    public void testSetJwtToken() {
        LoginResponse loginResponse = new LoginResponse(null);
        
        String newJwtToken = "newToken";
        loginResponse.setJwtToken(newJwtToken);
        
        assertEquals(newJwtToken, loginResponse.getJwtToken());
    }
}
