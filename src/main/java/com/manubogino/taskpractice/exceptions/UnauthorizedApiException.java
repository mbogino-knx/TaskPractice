package com.manubogino.taskpractice.exceptions;

import org.apache.http.HttpStatus;

public class UnauthorizedApiException extends ApiException {
    private static final String code = "Unauthorized";
    private static final Integer status_Code = HttpStatus.SC_UNAUTHORIZED;

    public UnauthorizedApiException(String description) {
        super(code, description, status_Code);
    }
}
