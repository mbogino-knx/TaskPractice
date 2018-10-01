package com.manubogino.taskpractice.controllers.tasks;

import com.manubogino.taskpractice.controllers.TaskController;
import com.manubogino.taskpractice.parsers.Parser;
import com.manubogino.taskpractice.services.TaskService;
import org.junit.Before;
import spark.Request;
import spark.Response;

import javax.validation.Validator;

import static org.mockito.Mockito.mock;

public abstract class BaseControllerTests {
    Request request;
    Response response;
    TaskController taskController;
    Validator validator;
    Parser parser;
    TaskService taskService;

    @Before
    public void init() {
        request = mock(Request.class);
        response = mock(Response.class);
        validator = mock(Validator.class);
        parser = mock(Parser.class);
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService, parser, validator);
    }
}