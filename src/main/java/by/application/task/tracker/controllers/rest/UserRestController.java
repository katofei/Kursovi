package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.service.TaskService;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profile/my-tasks/{id}")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {

//        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Task> taskList1 = taskService.getAllTasks();

        return new ResponseEntity<>(taskList1, HttpStatus.OK);
    }

}
