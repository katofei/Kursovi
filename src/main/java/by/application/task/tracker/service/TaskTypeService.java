package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskType;

import java.util.List;

public interface TaskTypeService {

    TaskType findTaskTypeById(Long taskTypeId);
    List<TaskType> getAllTaskTypes();
    TaskType findByTaskTypeName(String taskTypeName);
}
