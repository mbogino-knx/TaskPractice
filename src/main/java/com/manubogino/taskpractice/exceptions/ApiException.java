package com.manubogino.taskpractice.exceptions;

import org.apache.http.HttpStatus;

public abstract class ApiException extends Exception {
    private String code;
    private String description;
    private Integer statusCode;

    public ApiException(String code, String description, Integer statusCode) {
        super(description);
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

    public ApiException() {
        this("internal_error", "Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
