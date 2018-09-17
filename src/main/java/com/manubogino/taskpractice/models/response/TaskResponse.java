package com.manubogino.taskpractice.models.response;

import java.util.Date;

public class TaskResponse {
    private int id;

    private String name;

    private String description;

    private String userId;
    private Date creationDate;

    public TaskResponse(int id, String name, String description, String user, Date creationDate) {
        super();

        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = user;
        this.creationDate = creationDate;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}