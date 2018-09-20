package com.manubogino.taskpractice.exceptions;

import org.apache.http.HttpStatus;

public class BadRequestApiException extends ApiException {
    private static final String code = "Bad_Request";
    private static final Integer status_Code = HttpStatus.SC_BAD_REQUEST;

    public BadRequestApiException(String description) {
        super(code, description, status_Code);
    }
}
