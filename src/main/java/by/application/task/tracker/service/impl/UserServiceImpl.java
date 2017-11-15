package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.repositories.UserRepository;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER_ROLE = "USER";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionService positionService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private UserRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User createUser(UserDTO userDTO) {
        User createdUser = new User(userDTO);
        createdUser.setUserRole(roleService.findByRoleName(USER_ROLE));
        createdUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        createdUser.setProject(projectService.findByProjectId(userDTO.getProject()));
        createdUser.setProjectRole(projectRoleService.findProjectRoleById(userDTO.getProjectRole()));
        createdUser.setPosition(positionService.findPositionById(userDTO.getPosition()));
        createdUser.setQualification(qualificationService.findQualificationById(userDTO.getQualification()));
        return userRepository.save(createdUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
