package by.application.task.tracker.controllers;

import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static by.application.task.tracker.Constants.TASK_CREATION_NOTIFICATION;
import static by.application.task.tracker.Constants.TASK_MODIFICATION_NOTIFICATION;
import static by.application.task.tracker.Constants.USER_ASSIGN_NOTIFICATION;

@Controller
public class TaskController {

    @Autowired private UserService userService;
    @Autowired private TaskService taskService;
    @Autowired private TaskTypeService taskTypeService;
    @Autowired private TaskStatusService taskStatusService;
    @Autowired private TaskPriorityService taskPriorityService;
    @Autowired private ProjectService projectService;
    @Autowired private EmailService emailService;

    @RequestMapping(path = "/dashboard/{id}/task-creation", method = RequestMethod.GET)
    public ModelAndView getTaskCreationPage() {
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("allUsers", userService.getAllUsers(currentUser.getProject().getProjectId()));
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());
        TaskDTO taskForCreation = new TaskDTO();
        view.addObject("task", taskForCreation);
        return view;
    }

    @RequestMapping(path = "/dashboard/{id}/task-creation", method = RequestMethod.POST)
    public ModelAndView createTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("taskCreation");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("allUsers", userService.getAllUsers(currentUser.getProject().getProjectId()));
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());

        if (result.hasErrors()) {
            view.setViewName("taskCreation");
            return view;
        }
        view.setViewName("redirect:/dashboard/task/{id}");
        taskService.createTask(taskDTO);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userService.findUserById(taskDTO.getExecutor()).getUserContact().getWorkEmail());
        simpleMailMessage.setSubject(TASK_CREATION_NOTIFICATION);
        simpleMailMessage.setText("Dear user, be informed that you was assigned to " + taskDTO.getTaskName()
                + "You estimation date: " + taskDTO.getEstimation());
        simpleMailMessage.setFrom(Constants.from_email);
        emailService.sendEmail(simpleMailMessage);
        return view;
    }

    @RequestMapping(path = "/dashboard/{id}/allTasks", method = RequestMethod.GET)
    public ModelAndView getAllTasks(@PathVariable("id") long dashboardId) {
        ModelAndView view = new ModelAndView("allTasksPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<Task> taskList = taskService.getAllDashboardTasks(dashboardId);
        view.addObject("taskList", taskList);
        return view;
    }

    @RequestMapping(path = "/dashboard/{id}/task/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("task");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(id);
        view.addObject("taskType", task.getTaskType());
       // view.addObject("taskProject",task.getProject());
        view.addObject("taskPriority", task.getTaskPriority());
        view.addObject("taskStatus", task.getTaskStatus());
        view.addObject("creator", task.getCreator());
        view.addObject("executor", task.getExecutor());
        view.addObject("task", task);

        Date today = new Date();
        LocalDate date  = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        view.addObject("currentDate", date.toString());
        List<User> userList= userService.getAllUsers(currentUser.getProject().getProjectId());
        view.addObject("userList", userList);
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());


        view.addObject("userDTO", new UserDTO());
        view.addObject("taskDTO", new TaskDTO());
        return view;
    }

    @RequestMapping(value = "/dashboard/{id}/task-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteProject(@PathVariable("id") long taskId) {
        ModelAndView view = new ModelAndView("task");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("qualification", currentUser.getQualification());

       // projectService.deleteProject(taskId); WHATAFUCK!?!?!?!?!?
        view.setViewName("redirect:/dashboard/allTasks");
        return view;
    }

    @RequestMapping(value = "/dashboard/{id}/task-edition/{taskId}", method = RequestMethod.GET)
    public ModelAndView getTaskEditionPage(@PathVariable("taskId") long taskId) {
        ModelAndView view = new ModelAndView("editTaskPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(taskId);
        view.addObject("task", task);

        List<User> userList = userService.getAllUsers(currentUser.getProject().getProjectId());
        view.addObject("userList", userList);
        view.addObject("taskDTO", new TaskDTO());
        view.addObject("taskTypes", taskTypeService.getAllTaskTypes());
        return view;
    }

    @RequestMapping(value = "/dashboard/{id}/task-edition/{taskId}", method = RequestMethod.POST)
    public ModelAndView editTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result,
                                 @PathVariable("taskId") long taskId) {
        ModelAndView view = new ModelAndView("editTaskPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        Task task = taskService.findTaskById(taskId);
        view.addObject("taskType", task.getTaskType());
      //  view.addObject("taskProject",task.getProject());
        view.addObject("taskPriority", task.getTaskPriority());
        view.addObject("taskStatus", task.getTaskStatus());
        view.addObject("creator", task.getCreator());
        view.addObject("executor", task.getExecutor());
        view.addObject("task", task);

        List<User> userList = userService.getAllUsers(currentUser.getProject().getProjectId());
        view.addObject("userList", userList);
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());

        if (result.hasErrors()) {
            view.setViewName("taskCreation");
            return view;
        }
        view.setViewName("redirect:/dashboard/allTasks");
        taskService.editTask(taskDTO, taskId );
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userService.findUserById(taskDTO.getExecutor()).getUserContact().getWorkEmail());
        simpleMailMessage.setSubject(TASK_MODIFICATION_NOTIFICATION);
        simpleMailMessage.setText("Dear user, be informed that task " + taskDTO.getTaskName() + "was modified");
        simpleMailMessage.setFrom(Constants.from_email);
        emailService.sendEmail(simpleMailMessage);
        return view;
    }
}
