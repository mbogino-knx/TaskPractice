package com.manubogino.taskpractice.controllers.taskControllerTests;

import com.manubogino.taskpractice.exceptions.BadRequestApiException;
import com.manubogino.taskpractice.models.ErrorModel;
import com.manubogino.taskpractice.models.ValidationResult;
import com.manubogino.taskpractice.models.request.TaskRequest;
import com.manubogino.taskpractice.models.response.CreateTaskResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateTests extends BaseControllerTests {
    private final int taskId = 98;
    private TaskRequest taskRequest;

    private void initRequestResponse() {
        //add init de request y response comunes a todos los métodos
    }

    @Test
    public void createShouldReturnIdObjectCreatedWhenValidatorNotReturnsErrors() throws Exception {
        CreateTaskResponse taskResponse = new CreateTaskResponse(taskId);
        String parsedResponse = "parsedResponse";
        taskRequest = new TaskRequest();
        taskRequest.setName("name");

        final String errorMessage = "error message";
        final String propertyName = "prop name";

        Set<ConstraintViolation<Object>> errors = new HashSet<>();
        /*ConstraintViolation error = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn(propertyName);
        when(error.getPropertyPath()).thenReturn(path);
        when(error.getMessage()).thenReturn(errorMessage);
        errors.add(error);*/

        when(validator.validate(any())).thenReturn(errors);
        when(parser.parseToObject(any(), eq(TaskRequest.class))).thenReturn(taskRequest);
        when(taskService.addTask(any(TaskRequest.class))).thenReturn(taskResponse);
        when(parser.parseToString(any(CreateTaskResponse.class))).thenReturn(parsedResponse);

        String apiResponse = (String) taskController.createTask.handle(request, response);
        assertNotNull(apiResponse);
        assertEquals(parsedResponse, apiResponse);

        //como validar que creo el objeto realmente? pegandole a la api?
        verify(response, times(1)).status(HttpStatus.SC_CREATED);

        verify(parser, times(1)).parseToString(taskResponse);
    }

    @Test
    public void createShouldCallServiceAndReturnCreatedEntity() throws Exception {
        String parsedResponse = "parsedResponse";
        CreateTaskResponse taskResponse = new CreateTaskResponse(taskId);

        when(parser.parseToString(any())).thenReturn(parsedResponse);
        when(taskService.addTask(any(TaskRequest.class))).thenReturn(taskResponse);
        String result = (String) taskController.createTask.handle(request, response);

        assertNotNull(result);
        assertEquals(parsedResponse, result);

        verify(taskService, times(1)).addTask(taskRequest);

        verify(parser, times(1)).parseToString(taskResponse);
    }

    @Test
    public void createShouldReturnBadRequestApiExceptionWhenValidatorReturnsErrors() throws Exception {
        ValidationResult validationResult = new ValidationResult();
        List<ErrorModel> listError = new ArrayList<>();
        listError.add(new ErrorModel("property", "error"));
        validationResult.setErrors(listError);
        when(taskController.validateRequestBody(any(TaskRequest.class))).thenReturn(validationResult);

        taskController.createTask.handle(request, response);

        BadRequestApiException e = assertThrows(BadRequestApiException.class, () -> taskController.createTask.handle(request, response));
        assertNotNull(e.getMessage());
        assertFalse(e.getMessage().isEmpty());
        //que diferencia hay entre ellos??
        //verify(response, times(1)).status(HttpStatus.SC_BAD_REQUEST); validarlo con el modelo
    }

    @Test
    public void createShouldNotCreatedObjectWhenValidatorReturnsErrors() throws Exception {
        ValidationResult validationResult = new ValidationResult();
        List<ErrorModel> listError = new ArrayList<>();
        listError.add(new ErrorModel("property", "error"));
        validationResult.setErrors(listError);
        when(taskController.validateRequestBody(any(TaskRequest.class))).thenReturn(validationResult);

        taskController.createTask.handle(request, response);

        verify(taskService, never()).addTask(any(TaskRequest.class));
    }
}