package com.manubogino.taskpractice.repositories.tasks;

import com.manubogino.taskpractice.models.domain.Task;
import com.manubogino.taskpractice.repositories.implementations.TaskRepositoryImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AddTests {
    private TaskRepositoryImpl taskRepository;
    private SessionFactory sessionFactory;
    private Session session;
    private Task task;
    private final String userId = "28";
    private final String description = "description";
    private final String name = "name";
    private final int taskId = 14;

    @Before
    public void setup() {
        task = new Task();
        task.setUserId(userId);
        task.setDescription(description);
        task.setName(name);

        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        taskRepository = new TaskRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void createShouldCallSave() {
        taskRepository.add(task);
        verify(session, times(1)).save(any());
    }

    @Test
    public void saveParameterShouldHaveCreatedDate() {
        taskRepository.add(task);
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(session, times(1)).save(taskCaptor.capture());
        assertNotNull(taskCaptor.getValue().getCreationDate());
    }

    @Test
    public void createParameterShouldBeSameAsSaveParameter() {
        taskRepository.add(task);
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(session).save(taskCaptor.capture());
        assertEquals(task.getDescription(), taskCaptor.getValue().getDescription());
        assertEquals(task.getName(), taskCaptor.getValue().getName());
        assertEquals(task.getUserId(), taskCaptor.getValue().getUserId());
    }

    @Test
    public void createShouldReturnIdCreatedEntity() {
        when(session.save(any(Task.class))).thenAnswer((Answer<Void>) invocationOnMock -> {
            Task newTask = (Task) invocationOnMock.getArguments()[0];
            newTask.setId(taskId);
            return null;
        });

        int result = taskRepository.add(task);
        assertNotNull(result);
        assertEquals(taskId, result);
    }

    @Test
    public void createShouldReturnExceptionWhenSaveReturnsException() {
        when(session.save(any())).thenThrow(new HibernateException("") {
        });
        assertThrows(Exception.class, () -> taskRepository.add(any()));
    }
}
