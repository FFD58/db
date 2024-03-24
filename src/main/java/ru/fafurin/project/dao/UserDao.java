package ru.fafurin.project.dao;

import ru.fafurin.project.models.User;

import java.util.List;

public interface UserDao {
    User findFyId(long id);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(User user);
}
