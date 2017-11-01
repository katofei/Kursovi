package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskStatus;

import java.util.List;

public interface TaskStatusService {

    TaskStatus findTaskStatusById(Long taskStatusId);
    List<TaskStatus> getAllTaskStatuses();
    TaskStatus findByTaskStatusName(String taskStatusName);
}
