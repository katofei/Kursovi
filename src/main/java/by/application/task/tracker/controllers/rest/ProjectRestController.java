package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.Project;
import by.application.task.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/projects")
public class ProjectRestController {


    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public ResponseEntity getProject(@PathVariable("projectId") Long projectId){
        return new ResponseEntity<>(projectService.findProjectById(projectId), HttpStatus.OK);
    }

    @RequestMapping(value = "/All", method = RequestMethod.GET)
    public ResponseEntity getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @RequestMapping(value = "/project_add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.addProject(project), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectService.findProjectById(projectId), HttpStatus.OK);
    }
}
