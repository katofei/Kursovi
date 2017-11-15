package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.TaskType;

import java.util.List;

public interface TaskTypeService {

    TaskType findTaskByTypeId(Long taskTypeId);
    List<TaskType> getAllTaskTypes();
    TaskType findTaskByTypeName(String taskTypeName);
}
