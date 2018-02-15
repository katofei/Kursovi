package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskType;

import java.util.List;

public interface TaskTypeService {

    TaskType findTaskByTypeId(long taskTypeId);
    List<TaskType> getAllTaskTypes();
    TaskType findTaskByTypeName(String taskTypeName);
}
