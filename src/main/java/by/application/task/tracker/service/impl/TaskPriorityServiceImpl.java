package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.TaskPriority;
import by.application.task.tracker.repositories.TaskPriorityRepository;
import by.application.task.tracker.service.TaskPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskPriorityServiceImpl implements TaskPriorityService {

    @Autowired
    private TaskPriorityRepository taskPriorityRepository;


    @Override
    public TaskPriority findTaskPriorityById(Long taskPriorityId) {
        return taskPriorityRepository.findOne(taskPriorityId);
    }

    @Override
    public List<TaskPriority> getAllTaskPriorities() {
        List<TaskPriority> priorities = new ArrayList<>();
        taskPriorityRepository.findAll().forEach(priorities::add);
        return priorities;
    }

    @Override
    public TaskPriority findByTaskPriorityName(String taskPriorityName) {
        return taskPriorityRepository.findByPriorityName(taskPriorityName);
    }
}
