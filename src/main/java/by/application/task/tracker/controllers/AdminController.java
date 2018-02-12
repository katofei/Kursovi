package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView view = new ModelAndView("adminStartPage");
        view.addObject("currentUser", currentUser);
        /*
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));*/
        return view;
    }


    @RequestMapping(path = "/homePage", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("homePage");
    }
}
