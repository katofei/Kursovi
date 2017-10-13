package by.application.task.tracker.service;

import by.application.task.tracker.data.Task;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    void deleteTask(Long taskId);
    Task editTask(Task task);
    Task findTaskById(Long taskId);
    List<Task> getAllTasks();
}
