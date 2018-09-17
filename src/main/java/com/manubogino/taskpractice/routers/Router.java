package com.manubogino.taskpractice.routers;

import com.manubogino.taskpractice.controllers.TaskController;
import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.parsers.Parser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

public class Router implements SparkApplication {
    private final ApplicationContext context;
    private final TaskController taskController;
    private final Parser parser;

    public Router() {
        context = new ClassPathXmlApplicationContext("base-config.xml");
        taskController = context.getBean("taskController", TaskController.class);
        parser = context.getBean("parser", Parser.class);
    }

    public void init() {
//        Spark.before((request, response) -> {
//          Add filters to calls
//        });

        Spark.exception(Exception.class, this::ManageException);

        Spark.path("/api/tasks", () -> {
            Spark.get("/:id", taskController.getTaskById);
            Spark.get("", taskController.getTasks);
            Spark.post("", taskController.createTask);
            Spark.post("/:id/share", taskController.shareTask);
            Spark.put("", taskController.editTask);
            Spark.delete("/:id", taskController.removeTask);
            Spark.get("/:id/users", taskController.getUsersByTask);
        });
    }

    public void destroy() {
    }

    private void ManageException(Exception e, Request request, Response response) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            response.status(apiException.getStatusCode());
            try {
                response.body(parser.parseToString(apiException.getDescription()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
