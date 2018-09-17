package com.manubogino.taskpractice.exceptions;

import org.eclipse.jetty.http.HttpStatus;

public class BadRequestApiException extends ApiException {
    private static final String code = "Bad_Request";
    private static final Integer status_Code = HttpStatus.BAD_REQUEST_400;

    public BadRequestApiException(String description) {
        super(code, description, status_Code);
    }
}
