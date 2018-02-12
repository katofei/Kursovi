package by.application.task.tracker.controllers;


import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class DashboardController {

    @Autowired private ProjectService projectService;
    @Autowired private UserService userService;
    @Autowired private DashboardService dashboardService;
    @Autowired private DashboardStatusService dashboardStatusService;
    @Autowired private DashboardPriorityService dashboardPriorityService;
    @Autowired private QualificationService qualificationService;
    @Autowired private TaskService taskService;

    @RequestMapping(path = "/dashboard-creation", method = RequestMethod.GET)
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

    @RequestMapping(path = "/dashboard-creation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("project") DashboardDTO dashboardDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("dashboardCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        if (result.hasErrors()) {
            view.setViewName("dashboardCreation");
            return view;
        }
        view.setViewName("redirect:/allDashboards");
        dashboardService.createDashboard(dashboardDTO);
        return view;
    }

    @RequestMapping(path = "project/{id}/allDashboards", method = RequestMethod.GET)
    public ModelAndView getAllDashboards(@PathVariable("id") long projectId) {
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

    @RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
    public ModelAndView getProject(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("dashboard");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Dashboard dashboard = dashboardService.findByDashboardById(dashboardId);
        view.addObject("dashboard", dashboard);
        List<Task> taskList = taskService.getAllTasks(dashboardId);
        view.addObject("taskList", taskList);
        return view;
    }


    @RequestMapping(value = "/dashboard-edition/{id}", method = RequestMethod.GET)
    public ModelAndView getDashEdition(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("editDashboardPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        DashboardDTO dashboardDTO = new DashboardDTO();
        view.addObject("dashboard", dashboardDTO);
        return view;
    }

    @RequestMapping(value = "/dashboard-edition/{id}", method = RequestMethod.POST)
    public ModelAndView editDashboard(@Valid @ModelAttribute("project") DashboardDTO dashboardDTO, BindingResult result, @PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("editDashboardPage");
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.addObject("currentUser", currentUser);
            view.addObject("position", currentUser.getPosition());
            view.addObject("qualification", currentUser.getQualification());
            return view;
        }
        view.setViewName("redirect:/allDashboards");
        dashboardService.editDashboard(dashboardDTO, dashboardId);
        return view;
    }


    @RequestMapping(value = "/dashboard-deletion/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectDeletion(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("allDashboards");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        return new ModelAndView("allDashboards");
    }


    @RequestMapping(value = "/dashboard-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("allDashboards");
        dashboardService.deleteDashboard(dashboardId);
        return view;
    }
}
