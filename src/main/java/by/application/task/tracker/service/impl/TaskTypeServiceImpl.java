package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.TaskType;
import by.application.task.tracker.repositories.TaskTypeRepository;
import by.application.task.tracker.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService{

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Override
    public TaskType findTaskTypeById(Long taskTypeId) {
        return taskTypeRepository.findOne(taskTypeId);
    }

    @Override
    public List<TaskType> getAllTaskTypes() {
        List<TaskType> types = new ArrayList<>();
        taskTypeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public TaskType findByTaskTypeName(String taskTypeName) {
        return null;
    }
}
