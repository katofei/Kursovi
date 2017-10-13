package by.application.task.tracker.service;

import by.application.task.tracker.data.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void delete(Long userId);
    User edit(User user);
    User findById(Long userId);
    List<User> getAll();
}
