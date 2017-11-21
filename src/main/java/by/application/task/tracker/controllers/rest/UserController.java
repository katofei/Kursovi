package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
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
        ModelAndView view = new ModelAndView("userStartPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));
        return view;
    }

    @RequestMapping(path = "/allUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView view = new ModelAndView("allUsersPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        //  view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);
        return view;
    }

    @RequestMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getUser(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("profile");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        view.addObject("user", userService.findUserById(id));
        return view;
    }
}
