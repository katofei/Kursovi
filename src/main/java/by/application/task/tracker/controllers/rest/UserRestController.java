package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.repositories.UserRepository;
import by.application.task.tracker.service.ProjectService;
import by.application.task.tracker.service.UserService;
import by.application.task.tracker.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private final UserService userService;
    private final UserRepository repository;
    private final UserStatusService statusService;
    private final ProjectService projectService;

    @Autowired
    public UserRestController(UserService userService, UserRepository repository,
                              UserStatusService statusService, ProjectService projectService) {
        this.repository = repository;
        this.userService = userService;
        this.statusService = statusService;
        this.projectService = projectService;
    }


    @RequestMapping(value = "/user-assign/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> assignUser(@PathVariable(value = "userId") long userId,
                                        @RequestBody UserDTO userDTO) {
        User userById = userService.findUserById(userId);
        userById.setUserStatus(statusService.findByStatusName("Assigned"));
        userById.setEstimation(userDTO.getEstimation());
        userById.setProject(projectService.findByProjectId(userDTO.getProject()));
        repository.save(userById);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

}
