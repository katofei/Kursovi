package by.application.task.tracker.controllers;


import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import by.application.task.tracker.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static by.application.task.tracker.Constants.DASHBOARD_CREATION_NOTIFICATION;
import static by.application.task.tracker.Constants.TASK_MODIFICATION_NOTIFICATION;

@Controller
public class DashboardController implements CurrentUserController {

    private final ProjectService projectService;
    private final UserService userService;
    private final DashboardService dashboardService;
    private final DashboardPriorityService dashboardPriorityService;
    private final TaskService taskService;
    private final EmailService emailService;

    @Autowired
    public DashboardController(ProjectService projectService, UserService userService,
                               DashboardService dashboardService,
                               DashboardPriorityService dashboardPriorityService,
                               TaskService taskService, EmailService emailService) {
        this.projectService = projectService;
        this.userService = userService;
        this.dashboardService = dashboardService;
        this.dashboardPriorityService = dashboardPriorityService;
        this.taskService = taskService;
        this.emailService = emailService;
    }

    @RequestMapping(path = "project/{projectId}/dashboard-creation", method = RequestMethod.GET)
    public ModelAndView getProjectCreationPage(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("dashboardCreation");
        getCurrentUser(userService, view);
        view.addObject("project", projectService.findByProjectId(projectId));
        view.addObject("allUsers", userService.getAllUsers(projectId));
        view.addObject("dashboardPriorities", dashboardPriorityService.getAllDashboardPriorities());

        DashboardDTO dashboardDTO = new DashboardDTO();
        view.addObject("dashboard", dashboardDTO);
        return view;
    }

    @RequestMapping(path = "/dashboard-creation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("dashboard") DashboardDTO dashboardDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("dashboardCreation");
        getCurrentUser(userService, view);

        if (result.hasErrors()) {
            view.setViewName("project/"+ dashboardDTO.getProject() + "/dashboard-creation");
            return view;
        }
        view.setViewName("redirect:/project/"+ dashboardDTO.getProject() + "/allDashboards");
        dashboardService.createDashboard(dashboardDTO);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userService.findUserById(dashboardDTO.getReporter()).getUserContact().getWorkEmail());
        simpleMailMessage.setSubject(DASHBOARD_CREATION_NOTIFICATION);
        simpleMailMessage.setText("Dear user, be informed that you was assigned as reporter to \" " + dashboardDTO.getDashboardName() + " \" dashboard.\n"+
        "Your task is : " + dashboardDTO.getDescription()+".\n"+
        "It should be finished due to - " + dashboardDTO.getDueDate());
        simpleMailMessage.setFrom(Constants.from_email);
        emailService.sendEmail(simpleMailMessage);
        return view;
    }

    @RequestMapping(path = "project/{projectId}/allDashboards", method = RequestMethod.GET)
    public ModelAndView getAllDashboards(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("allDashboardsPage");
        getCurrentUser(userService, view);

        List<Dashboard> dashboards = dashboardService.getAllDashboards(projectId);
        view.addObject("dashboardList", dashboards);
        return view;
    }

    @RequestMapping(value = "project/{projectId}/dashboard/{dashboardId}", method = RequestMethod.GET)
    public ModelAndView getProject(@PathVariable("dashboardId") long dashboardId) {
        ModelAndView view = new ModelAndView("dashboard");
        getCurrentUser(userService, view);

        Dashboard dashboard = dashboardService.findByDashboardById(dashboardId);
        view.addObject("dashboard", dashboard);
        List<Task> taskList = taskService.getAllDashboardTasks(dashboardId);
        view.addObject("taskList", taskList);
        return view;
    }


    @RequestMapping(value = "project/{projectId}/dashboard-edition/{dashboardId}", method = RequestMethod.GET)
    public ModelAndView getDashEdition(@PathVariable("dashboardId") long dashboardId) {
        ModelAndView view = new ModelAndView("editDashboardPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        DashboardDTO dashboardDTO = new DashboardDTO();
        view.addObject("dashboard", dashboardDTO);
        return view;
    }

    @RequestMapping(value = "project/{projectId}/dashboard-edition/{dashboardId}", method = RequestMethod.POST)
    public ModelAndView editDashboard(@Valid @ModelAttribute("dashboard") DashboardDTO dashboardDTO, BindingResult result, @PathVariable("dashboardId") long dashboardId) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("editDashboardPage");
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.addObject("currentUser", currentUser);
            view.addObject("position", currentUser.getPosition());
            view.addObject("qualification", currentUser.getQualification());
            return view;
        }
        view.setViewName("redirect:project/{projectId}/allDashboards");
        dashboardService.editDashboard(dashboardDTO, dashboardId);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userService.findUserById(dashboardDTO.getReporter()).getUserContact().getWorkEmail());
        simpleMailMessage.setSubject(TASK_MODIFICATION_NOTIFICATION);
        simpleMailMessage.setText("Dear user, be informed that dashboard " + dashboardDTO.getDashboardName() + "was modified");
        simpleMailMessage.setFrom(Constants.from_email);
        emailService.sendEmail(simpleMailMessage);
        return view;
    }


    @RequestMapping(value = "project/{projectId}/dashboard-deletion/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectDeletion(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("allDashboards");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        //WHATAFUCK!?!?!?!??!?!
        return new ModelAndView("redirect:/project/{projectId}/allDashboards");
    }


    @RequestMapping(value = "project/{projectId}/dashboard-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("allDashboards");
        dashboardService.deleteDashboard(dashboardId);
        return view;
    }
}
