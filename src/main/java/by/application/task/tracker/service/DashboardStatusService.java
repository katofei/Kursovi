package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.DashboardStatus;

import java.util.List;

public interface DashboardStatusService {

    DashboardStatus findDashboardByStatusId(long dashboardStatusId);
    DashboardStatus findDashboardByStatusName(String dashboardStatusName);
    List<DashboardStatus> getAllDashboardStatuses();
}
