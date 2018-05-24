package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;

import java.util.List;

public interface TaskService {
    void assignAnotherUser(TaskDTO taskDTO, long id);
    void changePriority(TaskDTO taskDTO, long id);
    void changeStatus(TaskDTO taskDTO, long id);
    void createTask(TaskDTO taskDTO);
    void deleteTask(Long taskId);
    void editTask(TaskDTO taskDTO, long id);
    Task findTaskById(Long taskId);
    List<Task> getAllDashboardTasks(long dashboardId);
    List<Task> getAllUserTasks(long userId);
    List<Task> getAllTasks();

}
