package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.dto.UserDTO;
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
import java.util.stream.Collectors;

@Controller
public class TaskController {

    @Autowired private UserService userService;
    @Autowired private TaskService taskService;
    @Autowired private TaskTypeService taskTypeService;
    @Autowired private TaskStatusService taskStatusService;
    @Autowired private TaskPriorityService taskPriorityService;
    @Autowired private ProjectService projectService;

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

    @RequestMapping(path = "/profile/task-creation/{id}", method = RequestMethod.POST)
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
        view.setViewName("redirect:/profile/my-tasks/{id}");
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

    @RequestMapping(path = "/profile/{id}/task/{id}", method = RequestMethod.GET)
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

        List<User> userList= userService.getAllUsers()
                .stream().filter(user -> user.getProject() == currentUser.getProject()).collect(Collectors.toList());
        view.addObject("userList", userList);
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());


        view.addObject("userDTO", new UserDTO());
        view.addObject("taskDTO", new TaskDTO());
        return view;
    }

    @RequestMapping(value = "/profile/{id}/task-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("task");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

        projectService.deleteProject(id);
        return new ModelAndView("allTasks");
    }

    @RequestMapping(value = "profile/task-edition/{userId}/{taskId}", method = RequestMethod.GET)
    public ModelAndView getTaskEditionPage(@PathVariable("taskId") long taskId) {
        ModelAndView view = new ModelAndView("editTaskPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(taskId);
        view.addObject("task", task);

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);
        view.addObject("taskDTO", new TaskDTO());
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());

        return view;
    }

    @RequestMapping(value = "/profile/task-edition/{userId}/{taskId}", method = RequestMethod.POST)
    public ModelAndView editTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result,@PathVariable("taskId") long taskId) {
        ModelAndView view = new ModelAndView("editTaskPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(taskId);
        view.addObject("taskType", task.getTaskType());
        view.addObject("taskProject",task.getProject());
        view.addObject("taskPriority", task.getTaskPriority());
        view.addObject("taskStatus", task.getTaskStatus());
        view.addObject("creator", task.getCreator());
        view.addObject("executor", task.getExecutor());
        view.addObject("task", task);

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());

        if (result.hasErrors()) {
            view.setViewName("taskCreation");
            return view;
        }
        view.setViewName("redirect:/profile/{id}/my-tasks");
        taskService.editTask(taskDTO, taskId );
        return view;
    }
}
