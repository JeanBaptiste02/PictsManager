package com.epitech.pictsmanager.Responses;

/**
 * A generic response class representing an HTTP response message and status code
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
public class GenericResponse {
    private String message;
    private int httpCode;

    /**
     * Constructs a new GenericResponse with the provided message and HTTP status code
     * @param message  The response message.
     * @param httpCode The HTTP status code.
     */
    public GenericResponse(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    /**
     * Retrieves the response message
     * @return The response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the HTTP status code
     * @return The HTTP status code
     */
    public int getHttpCode() {
        return httpCode;
    }
}
