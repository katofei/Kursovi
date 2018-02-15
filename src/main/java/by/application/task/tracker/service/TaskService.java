package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;

import java.util.List;

public interface TaskService {
    Task assignAnotherUser(TaskDTO taskDTO, long id);
    Task changePriority(TaskDTO taskDTO, long id);
    Task changeStatus(TaskDTO taskDTO, long id);
    Task logTime(TaskDTO taskDTO, long id);
    Task createTask(TaskDTO taskDTO);
    void deleteTask(Long taskId);
    Task editTask(TaskDTO taskDTO, long id);
    Task findTaskById(Long taskId);
    List<Task> getAllDashboardTasks(long dashboardId);
    List<Task> getAllUserTasks(long userId);
    List<Task> getAllTasks();

}
