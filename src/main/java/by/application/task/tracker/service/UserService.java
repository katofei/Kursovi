package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    void deleteUser(Long userId);
    User editUser(User user);
    User findUserById(Long userId);
    List<User> getAllUsers();
    User findByUserName(String name);
    User findByLogin(String login);
}

