package com.manubogino.taskpractice.exceptions;

import org.apache.http.HttpStatus;

public class ParserException extends Exception {
    private String code;
    private String description;
    private Integer statusCode;

    private ParserException(String code, String description, int statusCode) {
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

    public ParserException(String description) {
        this("parser_exception", description, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}