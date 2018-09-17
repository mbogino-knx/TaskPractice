package com.manubogino.taskpractice.models.response;

public class CreateTaskResponse {
    private int id;

    public CreateTaskResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}