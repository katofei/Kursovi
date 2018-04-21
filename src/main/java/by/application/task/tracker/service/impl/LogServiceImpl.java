package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.LogDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.UserLog;
import by.application.task.tracker.repositories.DashboardRepository;
import by.application.task.tracker.repositories.TaskRepository;
import by.application.task.tracker.repositories.UserLogRepository;
import by.application.task.tracker.repositories.UserRepository;
import by.application.task.tracker.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final TaskRepository taskRepository;
    private final UserLogRepository logRepository;
    private final DataConverterService dataConverterService;
    private final DashboardRepository dashboardRepository;
    private final UserRepository userRepository;
    private final UserLogRepository loggerRepository;

    @Autowired
    public LogServiceImpl(TaskRepository taskRepository, UserLogRepository logRepository,
                          DataConverterService dataConverterService, DashboardRepository dashboardRepository,
                          UserRepository userRepository, UserLogRepository loggerRepository) {
        this.taskRepository = taskRepository;
        this.logRepository = logRepository;
        this.dataConverterService = dataConverterService;
        this.dashboardRepository = dashboardRepository;
        this.userRepository = userRepository;
        this.loggerRepository = loggerRepository;
    }

    //todo how to find by another field

    @Override
    public UserLog findByExecutorId(long userId) {
        return logRepository.findByExecutor(userRepository.findOne(userId));
    }

    @Override
    public UserLog logTime(LogDTO logDTO, long id) {
        UserLog userLog = new UserLog(logDTO);
        userLog.setExecutor(userRepository.findOne(logDTO.getExecutor()));
        userLog.setLogDate(dataConverterService.generateTodayStringDay());


        Task task = taskRepository.findOne(logDTO.getTask());
        task.setPercentage(logDTO.getPercentage());
        task.setTimeSpent(task.getTimeSpent() + logDTO.getTimeSpent());
        task.setUpdated(dataConverterService.generateTodayStringDay());
        if (task.getEstimation() != 0.0) {
            task.setEstimation(task.getEstimation() - logDTO.getTimeSpent());
        }
        taskRepository.save(task);
        Dashboard dashboard = task.getDashboard();
        dashboard.setTimeSpent(dashboard.getTimeSpent() + logDTO.getTimeSpent());
        if (dashboard.getEstimation() != 0.0) {
            dashboard.setEstimation(dashboard.getEstimation() - logDTO.getTimeSpent());
        }
        dashboardRepository.save(dashboard);
        return logRepository.save(userLog);
    }
}
