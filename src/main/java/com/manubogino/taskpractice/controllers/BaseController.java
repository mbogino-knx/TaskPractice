package com.manubogino.taskpractice.controllers;

import com.manubogino.taskpractice.models.ErrorModel;
import com.manubogino.taskpractice.models.ValidationResult;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
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

/*    void validateRequestBody(Object body) throws ApiException {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(body);
        if (!validationErrors.isEmpty()) {
            StringBuffer errorBuffer = new StringBuffer();
            errorBuffer.append("Fields with errors:");
            validationErrors.forEach(e -> errorBuffer.append(String.format(" [%s]", e.getPropertyPath(), e.getMessage())));
            List<ErrorModel> errors = new ArrayList<>();
            validationErrors.forEach(e ->
                    errors.add(new ErrorModel(e.getPropertyPath().toString(), e.getMessage()))
            );
            throw new BadRequestApiException(errorBuffer.toString());
        }
    }*/

    public ValidationResult validateRequestBody(Object body) {
        Set<ConstraintViolation<Object>> validationErrors = validator.validate(body);
        List<ErrorModel> errors = new ArrayList<>();
        ValidationResult result = new ValidationResult();
        result.setMessage("Los siguientes campos arrojaron errores de validación");
        if (!validationErrors.isEmpty()) {
            validationErrors.forEach(e ->
                    errors.add(new ErrorModel(e.getPropertyPath().toString(), e.getMessage())));
        }
        result.setErrors(errors);
        return result;
    }
}
