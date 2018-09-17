package com.manubogino.taskpractice.services;

import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.models.request.TaskRequest;
import com.manubogino.taskpractice.models.response.CreateTaskResponse;
import com.manubogino.taskpractice.models.response.TaskResponse;

import java.util.List;

public interface TaskService {

    CreateTaskResponse addTask(TaskRequest task) throws ApiException;

    TaskResponse editTask(int idTask, TaskRequest task) throws ApiException;

    void deleteTask(int idTask);

    TaskResponse getTask(int idTask);

    List<TaskResponse> getTasks();

    void shareTask();

    List<?> getUsers(int idTask);
}
