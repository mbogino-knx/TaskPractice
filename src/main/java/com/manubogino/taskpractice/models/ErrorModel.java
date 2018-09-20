package com.manubogino.taskpractice.models;

public class ErrorModel {
    public ErrorModel(String property, String error) {
        this.property = property;
        this.error = error;
    }

    private String property;
    private String error;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
