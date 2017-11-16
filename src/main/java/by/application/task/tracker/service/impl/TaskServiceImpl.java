package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.repositories.TaskRepository;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskPriorityService taskPriorityService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task createdTask = new Task(taskDTO);
        createdTask.setTaskStatus(taskStatusService.findTaskByStatusName("Open"));
        createdTask.setTaskPriority(taskPriorityService.findTaskByPriorityId(taskDTO.getTaskPriority()));
        createdTask.setTaskType(taskTypeService.findTaskByTypeId(taskDTO.getTaskType()));
        createdTask.setProject(projectService.findByProjectId(taskDTO.getProjectId()));
        createdTask.setCreator(userService.findUserById(taskDTO.getCreatorId()));
        createdTask.setExecutor(userService.findUserById(taskDTO.getExecutor()));
        return taskRepository.save(createdTask);
    }

    @Override
    public void deleteTask(Long task_id) {
        taskRepository.delete(task_id);
    }

    @Override
    public Task editTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(Long task_id) {
        return taskRepository.findOne(task_id);
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }
}
