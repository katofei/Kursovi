package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.repositories.UserRepository;
import by.application.task.tracker.service.ProjectService;
import by.application.task.tracker.service.UserService;
import by.application.task.tracker.service.UserStatusService;
import by.application.task.tracker.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static by.application.task.tracker.Constants.USER_ASSIGNED;
import static by.application.task.tracker.Constants.USER_ASSIGN_NOTIFICATION;

@RestController
@RequestMapping(value = "/allUsers")
public class UserRestController {

    private final UserService userService;
    private final UserRepository repository;
    private final ProjectService projectService;
    private final UserStatusService userStatusService;
    private final EmailService emailService;

    @Autowired
    public UserRestController(UserService userService, UserRepository repository,
                              ProjectService projectService,
                              UserStatusService userStatusService,
                              EmailService emailService) {
        this.repository = repository;
        this.userService = userService;
        this.projectService = projectService;
        this.userStatusService = userStatusService;
        this.emailService = emailService;
    }


    @RequestMapping(value = "/user-assign", method = RequestMethod.POST)
    public ResponseEntity<User> assignUser(@RequestBody UserDTO userDTO) {
        User userById = userService.findUserById(userDTO.getId());
        userById.setProject(projectService.findByProjectId(userDTO.getProject()));
        userById.setEstimation(userDTO.getEstimation());
        userById.setUserStatus(userStatusService.findByStatusName(USER_ASSIGNED));
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userById.getUserContact().getWorkEmail());
        registrationEmail.setSubject(USER_ASSIGN_NOTIFICATION);
        registrationEmail.setText("Dear user, be informed that you was assigned to " + projectService.findByProjectId(userDTO.getProject()).getProjectName()
                + "You estimation date: " + userDTO.getEstimation());
        registrationEmail.setFrom(Constants.from_email);
        emailService.sendEmail(registrationEmail);
        repository.save(userById);

        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

}
