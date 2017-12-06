package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    void deleteUser(Long userId);
    User editUser(UserDTO userDTO,User user);
    User findUserById(Long userId);
    List<User> getAllUsers();
    User findByUserName(String name);
    User findByLogin(String login);
}

