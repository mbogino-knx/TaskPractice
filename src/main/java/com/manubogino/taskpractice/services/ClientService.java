package com.manubogino.taskpractice.services;

import com.manubogino.taskpractice.models.domain.User;

import java.util.List;

public interface ClientService {
    User getUser(int idUser);

    List<User> getUsers();
}
