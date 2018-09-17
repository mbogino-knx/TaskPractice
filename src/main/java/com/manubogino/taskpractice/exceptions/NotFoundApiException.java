package com.manubogino.taskpractice.exceptions;

import org.eclipse.jetty.http.HttpStatus;

public class NotFoundApiException extends ApiException {
    private static final String code = "Not_Found";
    private static final Integer status_Code = HttpStatus.NOT_FOUND_404;

    public NotFoundApiException(String description) {
        super(code, description, status_Code);
    }
}
