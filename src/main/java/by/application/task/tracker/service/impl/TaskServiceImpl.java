package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.TaskPriority;
import by.application.task.tracker.data.entities.TaskStatus;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.repositories.TaskRepository;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskPriorityService taskPriorityService;
    private final TaskStatusService taskStatusService;
    private final TaskTypeService taskTypeService;
    private final UserService userService;
    private final DashboardService dashboardService;
    private final DataConverterService dataConverterService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskPriorityService taskPriorityService,
                           TaskStatusService taskStatusService, TaskTypeService taskTypeService,
                           UserService userService, DashboardService dashboardService,
                           DataConverterService dataConverterService) {
        this.taskRepository = taskRepository;
        this.taskPriorityService = taskPriorityService;
        this.taskStatusService = taskStatusService;
        this.taskTypeService = taskTypeService;
        this.userService = userService;
        this.dashboardService = dashboardService;
        this.dataConverterService = dataConverterService;
    }

    @Override
    public void assignAnotherUser(TaskDTO taskDTO, long id) {
        Task task = taskRepository.findOne(id);
        User user = userService.findUserById(taskDTO.getExecutor());
        task.setExecutor(user);
        task.setUpdated(dataConverterService.generateTodayStringDay());
        taskRepository.save(task);
    }

    @Override
    public void changePriority(TaskDTO taskDTO, long id) {
        Task task = taskRepository.findOne(id);
        TaskPriority taskPriority = taskPriorityService.findTaskByPriorityId(taskDTO.getTaskPriority());
        task.setTaskPriority(taskPriority);
        task.setUpdated(dataConverterService.generateTodayStringDay());
        taskRepository.save(task);
    }

    @Override
    public void changeStatus(TaskDTO taskDTO, long id) {
        Task task = taskRepository.findOne(id);
        TaskStatus taskStatus = taskStatusService.findTaskStatusById(taskDTO.getTaskStatus());
        task.setTaskStatus(taskStatus);
        task.setUpdated(dataConverterService.generateTodayStringDay());
        if (Objects.equals(taskStatus.getStatusName(), "Resolved")) {
            task.setResolved(dataConverterService.generateTodayStringDay());
        }
        taskRepository.save(task);
    }


    @Override
    public void createTask(TaskDTO taskDTO) {
        Task createdTask = new Task(taskDTO);
        createdTask.setCreated(dataConverterService.generateTodayStringDay());
        if (taskDTO.getDueDate() != null) {
            createdTask.setDueDate("Not specified");
        } else {
            createdTask.setDueDate(taskDTO.getDueDate());
        }
        if (taskDTO.getEstimation() == 0.0) {
            createdTask.setEstimation(0.0);
        } else {
            createdTask.setEstimation(taskDTO.getEstimation());
        }
        createdTask.setTaskStatus(taskStatusService.findTaskByStatusName("Open"));
        createdTask.setTaskPriority(taskPriorityService.findTaskByPriorityId(taskDTO.getTaskPriority()));
        createdTask.setTaskType(taskTypeService.findTaskByTypeId(taskDTO.getTaskType()));
        createdTask.setDashboard(dashboardService.findByDashboardById(taskDTO.getDashboard()));
        createdTask.setCreator(userService.findUserById(taskDTO.getCreator()));
        createdTask.setExecutor(userService.findUserById(taskDTO.getExecutor()));
        taskRepository.save(createdTask);
    }


    @Override
    public void deleteTask(Long task_id) {
        taskRepository.delete(task_id);
    }

    @Override
    public void editTask(TaskDTO taskDTO, long id) {
        Task editingTask = taskRepository.findOne(id);
        if (taskDTO.getTaskType() != 0) {
            editingTask.setTaskType(taskTypeService.findTaskByTypeId(taskDTO.getTaskType()));
        }
        if (taskDTO.getDueDate() != null) {
            editingTask.setDueDate("Not specified");
        } else {
            editingTask.setDueDate(taskDTO.getDueDate());
        }
        if (taskDTO.getDescription() != null) {
            editingTask.setDescription(taskDTO.getDescription());
        }
        editingTask.setUpdated(dataConverterService.generateTodayStringDay());
        editingTask.setDescription(taskDTO.getDescription());
        taskRepository.save(editingTask);
    }

    @Override
    public Task findTaskById(Long task_id) {
        return taskRepository.findOne(task_id);
    }

    @Override
    public List<Task> getAllDashboardTasks(long dashboardId) {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(task -> {
            if (task.getDashboard() == dashboardService.findByDashboardById(dashboardId)) {
                taskList.add(task);
            }
        });
        return taskList;
    }

    @Override
    public List<Task> getAllUserTasks(long userId) {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(task -> {
            if (task.getExecutor() == userService.findUserById(userId)) {
                taskList.add(task);
            }
        });
        return taskList;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

}
