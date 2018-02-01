package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;
import by.application.task.tracker.validation.exception.WorkEmailExistsException;
import by.application.task.tracker.validation.exception.LoginExistsException;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO) throws LoginExistsException, WorkEmailExistsException;
    void deleteUser(Long userId);
    User editUser(UserInfoWrapper userInfoWrapper);
    User findUserById(Long userId);
    List<User> getAllUsers();
    User findByUserName(String name);
    User findByLogin(String login);
  //  User findByWorkEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    void saveUser(User user);

}

