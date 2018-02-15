package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;

import java.util.List;

public interface DashboardService {
    Dashboard createDashboard(DashboardDTO dashboardDTO);
    void deleteDashboard(long dashboardId);
    Dashboard editDashboard(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard findByDashboardById(long dashboardId);
    List<Dashboard> getAllDashboards(long projectId);
    List<Dashboard> getAllDashboards();
    Dashboard changePriority(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard changeStatus(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard assignAnotherReporter(DashboardDTO dashboardDTO, long dashboardId);
}
