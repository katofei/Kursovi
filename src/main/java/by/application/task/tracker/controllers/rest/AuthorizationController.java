package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizationController {

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

    @RequestMapping(path = "/starter", method = RequestMethod.GET)
    public ModelAndView getStarterPage() {
        ModelAndView view = new ModelAndView("starter");
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", user);
        view.addObject("position",positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findProjectById(user.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(user.getQualification().getQualificationId()));
        return view;
    }
}
