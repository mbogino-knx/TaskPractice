package com.manubogino.taskpractice.controllers.taskControllerTests;

import com.manubogino.taskpractice.exceptions.ApiException;
import com.manubogino.taskpractice.exceptions.BadRequestApiException;
import com.manubogino.taskpractice.models.ValidationResult;
import com.manubogino.taskpractice.models.request.TaskRequest;
import com.manubogino.taskpractice.models.response.CreateTaskResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateTests extends BaseControllerTests {
    private final int taskId = 98;
    private final String taskName = "name";
    private final String parsedResponse = "parsedResponse";
    private TaskRequest taskRequest;
    private CreateTaskResponse taskResponse;

    @Test
    public void createShouldReturnObjectCreatedWhenValidationIsSuccess() throws Exception {
        initSuccessValidation();

        String apiResponse = (String) taskController.createTask.handle(request, response);
        assertNotNull(apiResponse);
        assertEquals(parsedResponse, apiResponse);

        verify(response, times(1)).status(HttpStatus.SC_CREATED);
        verify(parser, times(1)).parseToString(taskResponse);
    }

    @Test
    public void createShouldCallServiceWhenValidationIsSuccess() throws Exception {
        initSuccessValidation();

        taskController.createTask.handle(request, response);
        verify(taskService, times(1)).addTask(taskRequest);
    }

    @Test
    public void createShouldReturnBadRequestApiExceptionWhenValidationIsFalse() throws Exception {
        initFailedValidator();

        BadRequestApiException e = assertThrows(BadRequestApiException.class, () -> taskController.createTask.handle(request, response));

        assertNotNull(e.getDescription());
        assertEquals(parsedResponse, e.getDescription());
        //verify(response, times(1)).status(HttpStatus.SC_BAD_REQUEST); que el codigo de status que devuelve la excepcion sea un badrequesrt deberia validarse cuando se testea el modelo de exceptions
    }

    @Test
    public void createShouldNotCreatedObjectWhenValidationIsFalse() throws Exception {
        initFailedValidator();

        verify(taskService, never()).addTask(any(TaskRequest.class));
    }

    private void initSuccessValidation() throws ApiException {
        Set<ConstraintViolation<Object>> errors = new HashSet<>();
        when(validator.validate(any())).thenReturn(errors);

        taskRequest = new TaskRequest();
        taskRequest.setName(taskName);
        when(parser.parseToObject(any(), eq(TaskRequest.class))).thenReturn(taskRequest);

        taskResponse = new CreateTaskResponse(taskId);
        when(taskService.addTask(any(TaskRequest.class))).thenReturn(taskResponse);

        when(parser.parseToString(any(CreateTaskResponse.class))).thenReturn(parsedResponse);
    }

    private void initFailedValidator() throws ApiException {
        taskRequest = new TaskRequest();
        taskRequest.setName(taskName);
        when(parser.parseToObject(any(), eq(TaskRequest.class))).thenReturn(taskRequest);

        Set<ConstraintViolation<Object>> errors = new HashSet<>();
        final String errorMessage = "error message";
        final String propertyName = "property name";

        ConstraintViolation error = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn(propertyName);
        when(error.getPropertyPath()).thenReturn(path);
        when(error.getMessage()).thenReturn(errorMessage);
        errors.add(error);
        when(validator.validate(any())).thenReturn(errors);

        when(parser.parseToString(any(ValidationResult.class))).thenReturn(parsedResponse);
    }
}