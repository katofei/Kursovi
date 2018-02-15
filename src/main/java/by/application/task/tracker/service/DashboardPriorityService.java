package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.DashboardPriority;

import java.util.List;

public interface DashboardPriorityService {

    DashboardPriority findDashboardByPriorityId(long dashboardPriorityId);
    DashboardPriority findDashboardByPriorityName(String dashboardPriorityName);
    List<DashboardPriority> getAllDashboardPriorities();
}
