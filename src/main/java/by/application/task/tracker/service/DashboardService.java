package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;

import java.util.List;

public interface DashboardService {
    Dashboard createDashboard(DashboardDTO dashboardDTO);
    void deleteDashboard(long dashboardId);
    Dashboard editDashboard(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard findByDashboardById(long dashboardId);
    List<Dashboard> getAllProjectDashboards(long projectId);
    List<Dashboard> getAllUserDashboards(long userId);
    List<Dashboard> getAllDashboards();
    Dashboard changePriority(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard changeStatus(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard assignAnotherReporter(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard logTime(DashboardDTO dashboardDTO, long id);
}
