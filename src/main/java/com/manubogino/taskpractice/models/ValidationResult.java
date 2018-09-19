package com.manubogino.taskpractice.models;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private String message;
    private List<ErrorModel> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
