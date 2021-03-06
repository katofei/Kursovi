package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskPriority;

import java.util.List;

public interface TaskPriorityService {

    TaskPriority findTaskByPriorityId(long taskPriorityId);
    List<TaskPriority> getAllTaskPriorities();
    TaskPriority findTaskByPriorityName(String taskPriorityName);
}
