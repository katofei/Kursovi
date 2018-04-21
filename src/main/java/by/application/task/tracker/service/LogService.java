package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.LogDTO;
import by.application.task.tracker.data.entities.UserLog;

public interface LogService {
    UserLog findByExecutorId(long userId);
    UserLog logTime(LogDTO logDTO, long id);
}
