package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.Task;
import by.application.task.tracker.repositories.TaskRepository;
import by.application.task.tracker.service.TaskService;
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
