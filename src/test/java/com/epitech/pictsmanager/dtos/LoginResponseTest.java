package com.epitech.pictsmanager.dtos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoginResponseTest {

    @Test
    public void testGetJwtToken() {
        String jwtToken = "tokenex";
        LoginResponse loginResponse = new LoginResponse(jwtToken);
        
        assertEquals(jwtToken, loginResponse.getJwtToken());
    }

    @Test
    public void testSetJwtToken() {
        LoginResponse loginResponse = new LoginResponse(null);
        
        String newJwtToken = "newToken";
        loginResponse.setJwtToken(newJwtToken);
        
        assertEquals(newJwtToken, loginResponse.getJwtToken());
    }
}
