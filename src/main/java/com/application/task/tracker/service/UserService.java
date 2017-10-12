package com.application.task.tracker.service;

import com.application.task.tracker.data.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void delete(Long user_id);
    User edit(User user);
    User findById(Long user_id);
    List<User> getAll();
}
