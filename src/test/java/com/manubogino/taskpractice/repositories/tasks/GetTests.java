package com.manubogino.taskpractice.repositories.tasks;

import com.manubogino.taskpractice.models.domain.Task;
import com.manubogino.taskpractice.repositories.implementations.TaskRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GetTests {
    private TaskRepositoryImpl taskRepository;
    private SessionFactory sessionFactory;
    private Session session;
    private Query query;
    private Task task;
    private final int taskId = 14;
    private final String description = "description";
    private final String name = "name";
    private final String userId = "12";

    @Before
    public void setup() {
        task = new Task();
        task.setId(taskId);
        task.setDescription(description);
        task.setName(name);
        task.setUserId(userId);

        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        query = mock(Query.class);
        taskRepository = new TaskRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void getShouldCallGetQueryWithId() {
        when(session.createQuery(anyString(), any())).thenReturn(query);
        taskRepository.get(taskId);
        verify(session, times(1)).createQuery(anyString(), any());
    }
    //que llame una  vez al session.createQuery y que esa query, contenga el id

   /* @Test
    public void getParameterShouldBeSameAsCreateQueryParameter(){
        //when(session.createQuery(anyString(), any())).thenReturn(query);
        taskRepository.get(taskId);
        ArgumentCaptor<String> id = ArgumentCaptor.forClass(GetByIdSpecification.class);
        verify(session).createQuery(id.capture());
        assertEquals(taskId, id.getValue());
    }*/
    // que el objeto que le pasa al session.get sea el q le paso como parametro


    @Test
    public void getShouldReturnExceptionWhenCreateQueryReturnsException() {
        when(session.createQuery(anyString(), any())).thenThrow(new RuntimeException() {
        });
        assertThrows(Exception.class, () -> taskRepository.get(anyInt()));
    }

    @Test
    public void getShouldReturnNullWhenQueryNotReturnElements() {
        when(session.createQuery(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList<>());
        Task result = taskRepository.get(taskId);
        assertNull(result);
    }

    @Test
    public void getShouldReturnElement() {
        when(session.createQuery(anyString(), any())).thenReturn(query);

        List<Task> list = new ArrayList<>();

        list.add(task);
        when(query.getResultList()).thenReturn(list);

        Task result = taskRepository.get(taskId);
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals(description, result.getDescription());
        assertEquals(name, result.getName());
        assertEquals(userId, result.getUserId());
    }
}
