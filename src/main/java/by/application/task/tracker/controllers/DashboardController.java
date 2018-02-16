package by.application.task.tracker.controllers;


import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static by.application.task.tracker.Constants.DASHBOARD_CREATION_NOTIFICATION;
import static by.application.task.tracker.Constants.TASK_MODIFICATION_NOTIFICATION;

@Controller
public class DashboardController {

    @Autowired private ProjectService projectService;
    @Autowired private UserService userService;
    @Autowired private DashboardService dashboardService;
    @Autowired private DashboardStatusService dashboardStatusService;
    @Autowired private DashboardPriorityService dashboardPriorityService;
    @Autowired private QualificationService qualificationService;
    @Autowired private TaskService taskService;
    @Autowired private EmailService emailService;

    @RequestMapping(path = "project/{projectId}/dashboard-creation", method = RequestMethod.GET)
    public ModelAndView getProjectCreationPage() {
        ModelAndView view = new ModelAndView("dashboardCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        DashboardDTO dashboardDTO = new DashboardDTO();
        view.addObject("dashboard", dashboardDTO);
        return view;
    }

    @RequestMapping(path = "project/{projectId}/dashboard-creation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("dashboard") DashboardDTO dashboardDTO, BindingResult result, @MatrixVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("dashboardCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        if (result.hasErrors()) {
            view.setViewName("dashboardCreation");
            return view;
        }
        view.setViewName("redirect:/project/{projectId}/allDashboards");
        dashboardService.createDashboard(dashboardDTO);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userService.findUserById(dashboardDTO.getReporter()).getUserContact().getWorkEmail());
        simpleMailMessage.setSubject(DASHBOARD_CREATION_NOTIFICATION);
        simpleMailMessage.setText("Dear user, be informed that you was assigned as reporter to  " + dashboardDTO.getDashboardName() + " dashboard");
        simpleMailMessage.setFrom(Constants.from_email);
        emailService.sendEmail(simpleMailMessage);
        return view;
    }

    @RequestMapping(path = "project/{projectId}/allDashboards", method = RequestMethod.GET)
    public ModelAndView getAllDashboards(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("allDashboardsPage");
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        view.addObject("currentUser", user);
        view.addObject("position", user.getPosition());
        view.addObject("project", user.getProject());
        view.addObject("qualification", user.getQualification());

        List<Dashboard> dashboards = dashboardService.getAllDashboards(projectId);
        view.addObject("dashboardList", dashboards);
        return view;
    }

    @RequestMapping(value = "project/{projectId}/dashboard/{dashboardId}", method = RequestMethod.GET)
    public ModelAndView getProject(@PathVariable("dashboardId") long dashboardId) {
        ModelAndView view = new ModelAndView("dashboard");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

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
