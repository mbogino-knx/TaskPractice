package com.manubogino.taskpractice.repositories.implementations;

import com.manubogino.taskpractice.filters.Filter;
import com.manubogino.taskpractice.models.domain.Task;
import com.manubogino.taskpractice.repositories.TaskRepository;
import com.manubogino.taskpractice.specifications.SqlSpecification;
import com.manubogino.taskpractice.specifications.implementations.GetByIdSpecification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public TaskRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(Task object) {
        object.setCreationDate(new Date());
        session = sessionFactory.openSession();
        session.save(object);
        session.close();

        return object.getId();
    }

    @Override
    public void remove(Task object) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public void update(Task object) {

    }

    @Override
    @Transactional
    public Task get(int id) {
        SqlSpecification getById = new GetByIdSpecification<>(id, Task.class);
        Session session = sessionFactory.openSession();
        Query<Task> query = session.createQuery(getById.toSqlQuery(), Task.class);
        List<Task> tasks = query.getResultList();

        session.close();
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    @Override
    public Collection<Task> find(Filter filter) {
        return null;
    }
}
