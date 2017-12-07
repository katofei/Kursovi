package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Project;
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
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectContactService projectContactService;

    @RequestMapping(path = "/projectCreation", method = RequestMethod.GET)
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

    @RequestMapping(path = "/projectCreation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("projectCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification",currentUser.getQualification());

        if (result.hasErrors()) {
            view.setViewName("projectCreation");
            return view;
        }
        view.setViewName("project");
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

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public ModelAndView getProject(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("project");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("project", projectService.findByProjectId(id));
        return view;
    }


    @RequestMapping(value = "/project-edition/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectEdition(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("editProjectPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition().getPositionId());
        view.addObject("qualification",currentUser.getQualification().getQualificationId());

        Project editingProject = projectService.findByProjectId(id);
        view.addObject("project",editingProject);
        view.addObject("projectContact", editingProject.getProjectContact());

        return view;
    }

    @RequestMapping(value = "/project-edition/{id}", method = RequestMethod.POST)
    public ModelAndView editProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult result, @PathVariable("id") long id) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            view.setViewName("editProjectPage");
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.addObject("currentUser", currentUser);
            view.addObject("position",currentUser.getPosition());
            view.addObject("qualification", currentUser.getQualification());

            Project editingProject = projectService.findByProjectId(id);
            view.addObject("projectContact", editingProject.getProjectContact());
            return view;
        }
        view.setViewName("redirect:/allProjects");
        projectService.editProject(projectDTO, id);
        return view;
    }


    @RequestMapping(value = "/project-deletion/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectDeletion(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("allProjects");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        return new ModelAndView("allProjects");
    }


   @RequestMapping(value = "/project-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("allProjects");
        projectService.deleteProject(id);
        return new ModelAndView("allProjects");
    }
}
