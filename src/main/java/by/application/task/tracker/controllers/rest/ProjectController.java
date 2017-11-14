package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/project/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getProject(@PathVariable("id") long id) {
        Project project = projectService.findProjectById(id);
        ModelAndView view = new ModelAndView("project");
        view.addObject("project", project);
        return view;
    }
}
