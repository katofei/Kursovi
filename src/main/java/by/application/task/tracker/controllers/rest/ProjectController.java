package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.PositionService;
import by.application.task.tracker.service.ProjectService;
import by.application.task.tracker.service.QualificationService;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;

    @RequestMapping(path = "/projectCreation", method = RequestMethod.GET)
    public ModelAndView getProjectCreationPage() {
        ModelAndView view = new ModelAndView("projectCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        ProjectDTO projectDTO = new ProjectDTO();
        view.addObject("project", projectDTO);
        return view;
    }

    @RequestMapping(path = "/projectCreation", method = RequestMethod.POST)
    public ModelAndView createProject(@Valid @ModelAttribute("project")ProjectDTO projectDTO, BindingResult result){
        ModelAndView view = new ModelAndView("projectCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        if (result.hasErrors()) {
            view.setViewName("projectCreation");
            return view;
        }
        view.setViewName("project");
        Project createdTask = projectService.createProject(projectDTO);
        view.addObject("project", createdTask);
        return view;
    }

    @RequestMapping(path = "/allProjects", method = RequestMethod.GET)
    public ModelAndView getAllProjects() {
        ModelAndView view = new ModelAndView("allProjectsPage");
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        view.addObject("currentUser", user);
        view.addObject("position", positionService.findPositionById(user.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(user.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(user.getQualification().getQualificationId()));

        List<Project> projectList = projectService.getAllProjects();
        view.addObject("projectList", projectList);
        return view;
    }

    @RequestMapping(value = "/project/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getProject(@PathVariable("id") long id) {
        Project project = projectService.findByProjectId(id);
        ModelAndView view = new ModelAndView("project");
        view.addObject("project", project);
        return view;
    }
}
