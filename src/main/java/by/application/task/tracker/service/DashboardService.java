package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;

import java.util.List;

public interface DashboardService {
    Dashboard createDashboard(DashboardDTO dashboardDTO);
    void deleteDashboard(Long contactId);
    Dashboard editDashboard(DashboardDTO dashboardDTO, long dashboardId);
    Dashboard findByDashboardById(Long dashboardId);
    List<Dashboard> getAllDashboards(Long projectId);
    Dashboard changePriority(DashboardDTO dashboardDTO, long id);
    Dashboard changeStatus(DashboardDTO dashboardDTO, long id);
    Dashboard assignAnotherReporter(DashboardDTO dashboardDTO, long id);
}
