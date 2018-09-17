package com.manubogino.taskpractice.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskRequest {
    @NotNull
    @Size(min = 1, max = 100, message = "Longitud mínima 1 caracter y máxima 100 caracteres.")
    private String name;

    @NotNull
    @Size(min = 1, max = 100, message = "Longitud mínima 1 caracter y máxima 100 caracteres.")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
}
