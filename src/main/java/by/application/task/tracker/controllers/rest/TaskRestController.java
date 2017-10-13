package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.Task;
import by.application.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/application")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("projectId") Long taskId){
        return new ResponseEntity<>(taskService.findTaskById(taskId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks//All", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("projectId") Long taskId) {
        return new ResponseEntity<>(taskService.findTaskById(taskId), HttpStatus.OK);
    }
}
