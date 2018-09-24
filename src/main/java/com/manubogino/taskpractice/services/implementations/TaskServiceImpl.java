package com.manubogino.taskpractice.services.implementations;

import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.exceptions.NotFoundApiException;
import com.manubogino.taskpractice.models.domain.Task;
import com.manubogino.taskpractice.models.request.TaskRequest;
import com.manubogino.taskpractice.models.response.CreateTaskResponse;
import com.manubogino.taskpractice.models.response.TaskResponse;
import com.manubogino.taskpractice.services.TaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private String user = "2";
    //TODO -> agregar el user que corresponde
    private static HashMap<Integer, Task> taskMap = new HashMap<>();
    //ToDO -> reemplazar por bd

    @Override
    public CreateTaskResponse addTask(TaskRequest task) {
        int id = taskMap.size() + 1; //Esto va a ser reemplazado por el id autonumerico q la BD devuelva
        Task newTask = new Task();
        newTask.setId(id);
        newTask.setName(task.getName());
        newTask.setDescription(task.getDescription());
        newTask.setUserId(user);

        taskMap.put(id, newTask);
        return new CreateTaskResponse(id);
    }

    @Override
    public TaskResponse editTask(int idTask, TaskRequest newTask) throws ApiException {
        Task oldTask = taskMap.get(idTask);
        if (oldTask == null)
            throw new NotFoundApiException("No existe la tarea");
        //TODO -> Ver que excepción corresponde lanzar
        if (newTask.getDescription() != null)
            oldTask.setDescription(newTask.getDescription());
        if (newTask.getName() != null)
            oldTask.setName(newTask.getName());
        return map(oldTask);
    }

    public void deleteTask(int idTask) {
        taskMap.remove(idTask);
    }

    public TaskResponse getTask(int idTask) {
        Task task = taskMap.get(idTask);
        return map(task);
    }

    public List<TaskResponse> getTasks() {
        List<Task> tasks = new ArrayList<>(taskMap.values());
        List<TaskResponse> result = new ArrayList<>();
        for (Task task : tasks) {
            result.add(map(task));
        }
        return result;
    }

    public void shareTask() {
    }

    public List<?> getUsers(int idTask) {
        return null;
    }

    private TaskResponse map(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setDescription(task.getDescription());
        response.setUserId(task.getUserId());
        response.setCreationDate(task.getCreationDate());

        return response;
    }
}
