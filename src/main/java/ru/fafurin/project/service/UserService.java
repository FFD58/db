package ru.fafurin.project.service;

import ru.fafurin.project.dao.UserDao;
import ru.fafurin.project.models.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUser(int id) {
        return userDao.findFyId(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

}
