package com.application.task_tracker.service;

import com.application.task_tracker.data.entities.Task;

import java.util.List;

public interface TaskService {
    Task addUser(Task task);
    void delete(Long task_id);
    Task edit(Task task);
    Task findById(Long task_id);
    List<Task> getAll();
}
