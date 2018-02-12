package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.entities.UserContact;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;
import by.application.task.tracker.repositories.UserContactRepository;
import by.application.task.tracker.repositories.UserRepository;
import by.application.task.tracker.service.*;
import by.application.task.tracker.validation.exception.WorkEmailExistsException;
import by.application.task.tracker.validation.exception.LoginExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static by.application.task.tracker.Constants.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired private UserRepository userRepository;
    @Autowired private UserContactRepository userContactRepository;
    @Autowired private PositionService positionService;
    @Autowired private ProjectService projectService;
    @Autowired private ProjectRoleService projectRoleService;
    @Autowired private QualificationService qualificationService;
    @Autowired private UserRoleService roleService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserStatusService userStatusService;
    @Autowired private UserContactService userContactService;

    @Override
    public User createUser(UserDTO userDTO) throws LoginExistsException, WorkEmailExistsException {

        if (loginExists(userDTO.getLogin())) {
            throw new LoginExistsException("Account with such login already exists!");
        }
        if (emailExist(userDTO.getWorkEmail())) {
            throw new WorkEmailExistsException("Account with such email address already exists!");
        }
        User createdUser = new User(userDTO);
        createdUser.setUserRole(roleService.findByRoleName(USER_ROLE));
        createdUser.setProjectRole(projectRoleService.findProjectRoleById(userDTO.getProjectRole()));
        createdUser.setPosition(positionService.findPositionById(userDTO.getPosition()));
        createdUser.setProject(projectService.findByProjectId(userDTO.getProject()));
        createdUser.setUserStatus(userStatusService.findByStatusName(USER_NOT_ACTIVATED));
        createdUser.setQualification(qualificationService.findQualificationById(userDTO.getQualification()));
        createdUser.setEnabled(userDTO.isEnabled());
        createdUser.setConfirmationToken(userDTO.getConfirmationToken());
        createdUser.setUserContact(userContactService.createContact(userDTO));
        return userRepository.save(createdUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public User editUser(UserInfoWrapper userInfoWrapper) {
        User editedUser = userInfoWrapper.getUser();
        editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        editedUser.setProject(projectService.findByProjectId(editedUser.getProject().getProjectId()));
        editedUser.setProjectRole(projectRoleService.findProjectRoleById(editedUser.getProjectRole().getProjectRoleId()));
        editedUser.setPosition(positionService.findPositionById(editedUser.getPosition().getPositionId()));
        editedUser.setUserContact(userInfoWrapper.getUserContact());
        return userRepository.save(editedUser);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    private boolean loginExists(String login) {
        User user = userRepository.findByLogin(login);
        return user != null;
    }

    private boolean emailExist(String email) {
        UserContact userContact = userContactRepository.findByWorkEmail(email);
        return userContact != null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(long projectId) {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if(user.getProject() == projectService.findByProjectId(projectId)){
                userList.add(user);
            }
        });
        return userList;
    }
}
