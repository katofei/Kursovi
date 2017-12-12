package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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

    @RequestMapping(path = "/profile/{id}/task-creation", method = RequestMethod.GET)
    public ModelAndView getTaskCreationPage() {
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("allUsers", userService.getAllUsers());
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());
        TaskDTO taskForCreation = new TaskDTO();
        view.addObject("task", taskForCreation);
        return view;
    }

    @RequestMapping(path = "/profile/{id}/task-creation", method = RequestMethod.POST)
    public ModelAndView createTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("allUsers", userService.getAllUsers());
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());

        if (result.hasErrors()) {
            view.setViewName("taskCreation");
            return view;
        }
        view.setViewName("redirect:/profile/{id}/my-tasks");
        taskService.createTask(taskDTO);
        return view;
    }

    @RequestMapping(path = "/allTasks", method = RequestMethod.GET)
    public ModelAndView getAllTasks() {
        ModelAndView view = new ModelAndView("allTasksPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<Task> taskList = taskService.getAllTasks();
        view.addObject("taskList", taskList);
        return view;
    }

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("task");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(id);
        view.addObject("taskType", task.getTaskType());
        view.addObject("taskProject",task.getProject());
        view.addObject("taskPriority", task.getTaskPriority());
        view.addObject("taskStatus", task.getTaskStatus());
        view.addObject("creator", task.getCreator());
        view.addObject("executor", task.getExecutor());
        view.addObject("task", task);


        view.addObject("userDTO", new UserDTO());
        return view;
    }

    @RequestMapping(value = "/task-deletion/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteProject(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("task");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        projectService.deleteProject(id);
        return new ModelAndView("allTasks");
    }
}
