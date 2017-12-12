package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {
    @RequestMapping(value = "/assignAnotherUser", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> postUniversity(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUserName());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}