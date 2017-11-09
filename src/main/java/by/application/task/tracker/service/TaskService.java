package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.TaskDTO;
import by.application.task.tracker.data.entities.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskDTO taskDTO);
    void deleteTask(Long taskId);
    Task editTask(Task task);
    Task findTaskById(Long taskId);
    List<Task> getAllTasks();
}
