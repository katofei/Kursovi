package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(path = "/userPage", method = RequestMethod.GET)
    public ModelAndView getUserStartPage() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView view = new ModelAndView("userStartPage");
        view.addObject("currentUser", user);
        view.addObject("position", positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findProjectById(user.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(user.getQualification().getQualificationId()));
        return view;
    }

    @RequestMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getUser(@PathVariable("login") String login) {
        User user = userService.findByLogin(login);
        ModelAndView view = new ModelAndView("profile");
        view.addObject("currentUser", user);
        view.addObject("position", positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findProjectById(user.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(user.getQualification().getQualificationId()));
        return view;
    }
}
