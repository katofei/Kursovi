package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

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

    @RequestMapping(path = "/adminPage", method = RequestMethod.GET)
    public ModelAndView getAdminStartPage() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView view = new ModelAndView("adminStartPage");
        view.addObject("currentUser", user);
        view.addObject("position",positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(user.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(user.getQualification().getQualificationId()));
        return view;
    }

    @RequestMapping(path = "/allUser", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<User> userList = userService.getAllUsers();
        ModelAndView view = new ModelAndView("allUserPage");
        view.addObject("currentUser", user);
        view.addObject("position", positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(user.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(user.getQualification().getQualificationId()));

        view.addObject("userList", userList);
        return view;
    }

    @RequestMapping(path = "/allProjects", method = RequestMethod.GET)
    public ModelAndView getAllProjects() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Project> projectsList = projectService.getAllProjects();
        ModelAndView view = new ModelAndView("allProjectsPage");
        view.addObject("currentUser", user);
        view.addObject("position", positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(user.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(user.getQualification().getQualificationId()));

        view.addObject("userList", projectsList);
        return view;
    }


}
