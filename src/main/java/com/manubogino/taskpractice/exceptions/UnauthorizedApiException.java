package com.manubogino.taskpractice.exceptions;

import org.eclipse.jetty.http.HttpStatus;

public class UnauthorizedApiException extends ApiException {
    private static final String code = "Unauthorized";
    private static final Integer status_Code = HttpStatus.UNAUTHORIZED_401;

    public UnauthorizedApiException(String description) {
        super(code, description, status_Code);
    }
}
