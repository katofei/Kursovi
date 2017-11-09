package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskPriority;

import java.util.List;

public interface TaskPriorityService {

    TaskPriority findTaskPriorityById(Long taskPriorityId);
    List<TaskPriority> getAllTaskPriorities();
    TaskPriority findByTaskPriorityName(String taskPriorityName);
}
