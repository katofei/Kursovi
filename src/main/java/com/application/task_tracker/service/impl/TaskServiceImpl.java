package com.application.task_tracker.service.impl;

import com.application.task_tracker.data.entities.Task;
import com.application.task_tracker.repositories.TaskRepository;
import com.application.task_tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addUser(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long task_id) {
        taskRepository.delete(task_id);
    }

    @Override
    public Task edit(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findById(Long task_id) {
        return taskRepository.findOne(task_id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }
}
