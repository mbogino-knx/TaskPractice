package com.manubogino.taskpractice.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskResponse {
    private int id;
    private String name;
    private String description;
    private String userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
}