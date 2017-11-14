package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        view.addObject("project", projectService.findProjectById(user.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(user.getQualification().getQualificationId()));
        return view;
    }
}
