package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile/{id}/task/{id}")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/assignAnotherUser", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> assignAnotherUser(@PathVariable("id") long id, @RequestBody TaskDTO taskDTO) {
        assignAnotherUser(taskDTO, id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/changePriority", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> changePriority(@RequestBody TaskDTO userDTO) {
        System.out.println(userDTO.getExecutor());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> changeStatus(@RequestBody TaskDTO userDTO) {
        System.out.println(userDTO.getExecutor());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "/logTime", method = RequestMethod.POST)
    public ResponseEntity<TaskDTO> logTime(@RequestBody TaskDTO userDTO) {
        System.out.println(userDTO.getExecutor());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    private void assignAnotherUser(TaskDTO taskDTO, long id){
        taskService.assignAnotherUser(taskDTO, id);
    }
}
