package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.wrapper.ProjectInfoWrapper;
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
public class ProjectController {

    @Autowired private ProjectService projectService;
    @Autowired private UserService userService;
    @Autowired private PositionService positionService;
    @Autowired private QualificationService qualificationService;
    @Autowired private ProjectContactService projectContactService;
    @Autowired private DashboardService dashboardService;
    @Autowired private EmailService emailService;

    @RequestMapping(path = "/project-creation", method = RequestMethod.GET)
    public ModelAndView getProjectCreationPage() {
        ModelAndView view = new ModelAndView("projectCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        ProjectDTO projectDTO = new ProjectDTO();
        view.addObject("project", projectDTO);
        return view;
    }

    @RequestMapping(path = "/project-creation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("projectCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification",currentUser.getQualification());

        if (result.hasErrors()) {
            view.setViewName("project-creation");
            return view;
        }
        view.setViewName("redirect:/allProjects");
        projectService.createProject(projectDTO);
        return view;
    }

    @RequestMapping(path = "/allProjects", method = RequestMethod.GET)
    public ModelAndView getAllProjects() {
        ModelAndView view = new ModelAndView("allProjectsPage");
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        view.addObject("currentUser", user);
        view.addObject("position", user.getPosition());
        view.addObject("project", user.getProject());
        view.addObject("qualification", user.getQualification());

        List<Project> projectList = projectService.getAllProjects();
        view.addObject("projectList", projectList);
        return view;
    }

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
    public ModelAndView getProject(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("project");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Project project = projectService.findByProjectId(projectId);
        view.addObject("project", project);
        view.addObject("projectContact", project.getProjectContact());
        List<User> userList= userService.getAllUsers(projectId);
        List<Dashboard> dashboardList = dashboardService.getAllDashboards(projectId);
        view.addObject("userList", userList);
        view.addObject("dashboardList" , dashboardList);
        return view;
    }


    @RequestMapping(value = "/project-edition/{projectId}", method = RequestMethod.GET)
    public ModelAndView getProjectEdition(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("editProjectPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification",currentUser.getQualification());

        Project editingProject = projectService.findByProjectId(projectId);
        ProjectInfoWrapper projectInfoWrapper = new ProjectInfoWrapper(editingProject, editingProject.getProjectContact());
        view.addObject("project",projectInfoWrapper);
        return view;
    }

    @RequestMapping(value = "/project-edition/{projectId}", method = RequestMethod.POST)
    public ModelAndView editProject(@Valid @ModelAttribute("project") ProjectInfoWrapper projectInfoWrapper, BindingResult result,
                                    @PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("editProjectPage");
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.addObject("currentUser", currentUser);
            view.addObject("position",currentUser.getPosition());
            view.addObject("qualification", currentUser.getQualification());

            Project editingProject = projectService.findByProjectId(projectId);
            view.addObject("projectContact", editingProject.getProjectContact());
            return view;
        }
        view.setViewName("redirect:/allProjects");

        editProject(projectInfoWrapper);

        return view;
    }


    @RequestMapping(value = "/project-deletion/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectDeletion(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("allProjects");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        return new ModelAndView("allProjects");
    }


   @RequestMapping(value = "/project-deletion/{projectId}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("projectId") long projectId) {
        ModelAndView view = new ModelAndView("allProjects");
        projectService.deleteProject(projectId);
        return  view;
    }

    private void editProject(ProjectInfoWrapper projectInfoWrapper){
        projectService.editProject(projectInfoWrapper);
        projectContactService.editContact(projectInfoWrapper);
    }
}
