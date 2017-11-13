package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class TaskController {

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
    private ProjectService projectService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectRoleService projectRoleService;

    @RequestMapping(path = "/taskCreation", method = RequestMethod.GET)
    public ModelAndView getTaskCreationPage() {
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("allUsers", userService.getAllUsers());
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));
        view.addObject("taskTypes",taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());
        view.addObject("project", projectService.findProjectById(currentUser.getProject().getProjectId()));
        TaskDTO taskForCreation = new TaskDTO();
        view.addObject("task", taskForCreation);
        return view;
    }
    @RequestMapping(path = "/taskCreation", method = RequestMethod.POST)
    public ModelAndView createTask(@Valid @ModelAttribute("task")TaskDTO taskDTO, BindingResult result){
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        view.addObject("taskTypes",taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());
        view.addObject("project", projectService.findProjectById(currentUser.getProject().getProjectId()));

        if (result.hasErrors()) {
            view.setViewName("taskCreation");
            return view;
        }
        view.setViewName("task");
        Task createdTask = taskService.createTask(taskDTO);
        view.addObject("task", createdTask);
        return view;
    }


    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskPage(@PathVariable("id") long id) {
        Task task = taskService.findTaskById(id);
        ModelAndView view = new ModelAndView("task");
        view.addObject("task", task);
        return view;
    }
}
