package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.TaskStatus;
import by.application.task.tracker.repositories.TaskStatusRepository;
import by.application.task.tracker.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Override
    public TaskStatus findTaskStatusById(Long taskStatusId) {
        return taskStatusRepository.findOne(taskStatusId);
    }

    @Override
    public List<TaskStatus> getAllTaskStatuses() {
        List<TaskStatus> statuses = new ArrayList<>();
        taskStatusRepository.findAll().forEach(statuses::add);
        return statuses;
    }

    @Override
    public TaskStatus findTaskByStatusName(String taskStatusName) {
        return taskStatusRepository.findByStatusName(taskStatusName);
    }
}
