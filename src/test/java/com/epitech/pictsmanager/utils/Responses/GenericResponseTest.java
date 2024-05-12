package com.epitech.pictsmanager.utils.Responses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericResponseTest {

    @Test
    void testConstructor() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(message, response.getMessage());
        assertEquals(httpCode, response.getHttpCode());
    }

    @Test
    void testGetMessage() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(message, response.getMessage());
    }

    @Test
    void testGetHttpCode() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(httpCode, response.getHttpCode());
    }
}