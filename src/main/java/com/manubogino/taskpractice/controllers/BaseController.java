package com.manubogino.taskpractice.controllers;

import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.exceptions.BadRequestApiException;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

abstract class BaseController {
    private final Validator validator;
    private static final String REQUEST_IS_REQUIRED_MESSAGE = "Request is required";
    private static final String RESPONSE_IS_REQUIRED_MESSAGE = "Response is required";

    BaseController(Validator validator) {
        this.validator = validator;
    }

    void validateRequest(Request request) {
        Objects.requireNonNull(request, REQUEST_IS_REQUIRED_MESSAGE);
    }

    void validateResponse(Response response) {
        Objects.requireNonNull(response, RESPONSE_IS_REQUIRED_MESSAGE);
    }

    void validateRequestBody(Object body) throws ApiException {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(body);
        if (!validationErrors.isEmpty()) {
            StringBuffer errorBuffer = new StringBuffer();
            errorBuffer.append("Fields with errors:");
            validationErrors.forEach(e -> errorBuffer.append(String.format(" [%s]", e.getMessage())));
            throw new BadRequestApiException(errorBuffer.toString());
        }
    }
}