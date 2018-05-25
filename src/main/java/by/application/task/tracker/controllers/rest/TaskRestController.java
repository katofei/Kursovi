package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.LogDTO;
import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.service.LogService;
import by.application.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/task/{id}")
public class TaskRestController {

    private final TaskService taskService;
    private final LogService logService;

    @Autowired
    public TaskRestController(TaskService taskService, LogService logService) {
        this.taskService = taskService;
        this.logService = logService;
    }

    @RequestMapping(value = "/assignAnotherUser", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> assignAnotherUser(@PathVariable("id") long id, @RequestBody TaskDTO taskDTO) {
        assignAnotherUser(taskDTO, id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/changePriority", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> changePriority(@PathVariable("id") long id, @RequestBody TaskDTO taskDTO) {
        changePriority(taskDTO, id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> changeStatus(@PathVariable("id") long id, @RequestBody TaskDTO taskDTO) {
        changeStatus(taskDTO, id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/logTime", method = RequestMethod.POST)
    public ResponseEntity<LogDTO> logTime(@PathVariable("id") long id, @RequestBody LogDTO logDTO) {
        logDTO.setTask(id);
        logTime(logDTO, id);
        return new ResponseEntity<>(logDTO, HttpStatus.OK);
    }

    private void assignAnotherUser(TaskDTO taskDTO, long id){
        taskService.assignAnotherUser(taskDTO, id);
    }
    private void changePriority(TaskDTO taskDTO, long id){
        taskService.changePriority(taskDTO, id);
    }
    private void changeStatus(TaskDTO taskDTO, long id){
        taskService.changeStatus(taskDTO, id);
    }
    private void logTime(LogDTO logDTO, long id){ logService.logTime(logDTO, id); }
}
