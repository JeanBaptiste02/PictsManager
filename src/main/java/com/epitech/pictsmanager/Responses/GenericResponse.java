package com.epitech.pictsmanager.Responses;

public class GenericResponse {
    private String message;
    private int httpCode;

    public GenericResponse(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
