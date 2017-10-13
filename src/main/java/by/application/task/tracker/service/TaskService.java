package by.application.task.tracker.service;

import by.application.task.tracker.data.Task;

import java.util.List;

public interface TaskService {
    Task addUser(Task task);
    void delete(Long taskId);
    Task edit(Task task);
    Task findById(Long taskId);
    List<Task> getAll();
}
