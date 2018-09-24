package com.manubogino.taskpractice.repositories;

import com.manubogino.taskpractice.filters.Filter;

import java.util.Collection;

public interface BaseRepository<T> {
    int add(T object);

    void remove(T object);

    void removeById(int id);

    void update(T object);

    T get(int id);

    Collection<T> find(Filter filter);
}
