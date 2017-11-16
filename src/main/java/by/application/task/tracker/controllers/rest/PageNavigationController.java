package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageNavigationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskPriorityService taskPriorityService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private ProjectService projectService;


    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView getProfilePage(@PathVariable("id") long id) {
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView view = new ModelAndView("profile");
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        User user = userService.findUserById(id);
        view.addObject("currentUser", user);
        view.addObject("position",positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(user.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(user.getQualification().getQualificationId()));

        return view;
    }

    @RequestMapping(path = "/homePage", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("homePage");
    }

}