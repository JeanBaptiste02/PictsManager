package com.epitech.pictsmanager.utils.Responses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GenericResponse
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class GenericResponseTest {

	/**
     * Test constructor
     */
    @Test
    void testConstructor() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(message, response.getMessage());
        assertEquals(httpCode, response.getHttpCode());
    }

    /**
     * Test getMessage method
     */
    @Test
    void testGetMessage() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(message, response.getMessage());
    }

    /**
     * Test getHttpCode method
     */
    @Test
    void testGetHttpCode() {
        String message = "Test message";
        int httpCode = 200;

        GenericResponse response = new GenericResponse(message, httpCode);

        assertEquals(httpCode, response.getHttpCode());
    }
}