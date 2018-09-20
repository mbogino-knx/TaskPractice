package com.manubogino.taskpractice.exceptions;

import org.apache.http.HttpStatus;

public class NotFoundApiException extends ApiException {
    private static final String code = "Not_Found";
    private static final Integer status_Code = HttpStatus.SC_NOT_FOUND;

    public NotFoundApiException(String description) {
        super(code, description, status_Code);
    }
}
