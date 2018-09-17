package com.manubogino.taskpractice.controllers;

import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.models.request.TaskRequest;
import com.manubogino.taskpractice.models.response.CreateTaskResponse;
import com.manubogino.taskpractice.models.response.TaskResponse;
import com.manubogino.taskpractice.parsers.Parser;
import com.manubogino.taskpractice.services.TaskService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.validation.Validator;

public class TaskController extends BaseController {
    private final TaskService taskService;
    private final Parser parser;

    public TaskController(TaskService taskService, Parser parser, Validator validator) {
        super(validator);
        this.taskService = taskService;
        this.parser = parser;
    }

    public Route createTask = this::create;
    public Route editTask = this::edit;
    public Route removeTask = this::remove;
    public Route getTaskById = this::getById;
    public Route getTasks = this::getAll;
    public Route shareTask = this::share;
    public Route getUsersByTask = this::getUsersByTask;

    private String create(Request request, Response response) throws ApiException {
        validateRequest(request);
        validateResponse(response);

        TaskRequest newTask = parser.parseToObject(request.body(), TaskRequest.class);
        validateRequestBody(newTask);
        CreateTaskResponse result = taskService.addTask(newTask);

        response.status(HttpStatus.OK_200);
        return parser.parseToString(result);
    }

    private String edit(Request request, Response response) throws Exception {
        TaskRequest task = parser.parseToObject(request.body(), TaskRequest.class);
        TaskResponse result = taskService.editTask(Integer.parseInt(request.params(":id")), task);
        return parser.parseToString(result);
    }

    private String remove(Request request, Response response) {
        taskService.deleteTask(Integer.parseInt(request.params(":id")));
        return "Delete Task";
    }

    private String getById(Request request, Response response) throws Exception {
        TaskResponse result = taskService.getTask(Integer.parseInt(request.params(":id")));
        return parser.parseToString(result);
    }

    private String getAll(Request request, Response response) throws Exception {
        return parser.parseToString(taskService.getTasks());
    }

    private String share(Request request, Response response) {
        taskService.shareTask();
        return "";
    }

    private String getUsersByTask(Request request, Response response) {
        //Collection<>
        taskService.getUsers(Integer.parseInt(request.params(":id")));
        return "";
    }
}
