package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.repositories.DashboardRepository;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private DashboardStatusService dashboardStatusService;
    @Autowired
    private DashboardPriorityService dashboardPriorityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DataConverterService dataConverterService;

    @Override
    public Dashboard createDashboard(DashboardDTO dashboardDTO) {
        Dashboard createdDashboard = new Dashboard(dashboardDTO);
        createdDashboard.setCreated(dataConverterService.generateTodayStringDay());
        if (dashboardDTO.getDueDate().isEmpty()) {
            createdDashboard.setDueDate("Not specified");
        } else {
            createdDashboard.setDueDate(dashboardDTO.getDueDate());
        }
        if (dashboardDTO.getEstimation() == 0.0) {
            createdDashboard.setEstimation(0.0);
        } else {
            createdDashboard.setEstimation(dashboardDTO.getEstimation());
        }
        createdDashboard.setStatus(dashboardStatusService.findDashboardByStatusName("Open"));
        createdDashboard.setPriority(dashboardPriorityService.findDashboardByPriorityId(dashboardDTO.getPriority()));
        createdDashboard.setCreator(userService.findUserById(dashboardDTO.getCreator()));
        createdDashboard.setReporter(userService.findUserById(dashboardDTO.getReporter()));
        createdDashboard.setDescription(dashboardDTO.getDescription());
        return dashboardRepository.save(createdDashboard);
    }

    @Override
    public void deleteDashboard(long dashboardId) {
        dashboardRepository.delete(dashboardId);
    }

    @Override
    public Dashboard editDashboard(DashboardDTO dashboardDTO, long dashboardId) {
        Dashboard dashboard = dashboardRepository.findOne(dashboardId);
        dashboard.setUpdated(dataConverterService.generateTodayStringDay());
        if (dashboardDTO.getDueDate().isEmpty()) {
            dashboard.setDueDate("Not specified");
        } else {
            dashboard.setDueDate(dashboardDTO.getDueDate());
        }
        if (dashboardDTO.getEstimation() == 0.0) {
            dashboard.setEstimation(0.0);
        } else {
            dashboard.setEstimation(dashboardDTO.getEstimation());
        }
        dashboard.setStatus(dashboardStatusService.findDashboardByStatusName("Open"));
        dashboard.setPriority(dashboardPriorityService.findDashboardByPriorityId(dashboardDTO.getPriority()));
        dashboard.setCreator(userService.findUserById(dashboardDTO.getCreator()));
        dashboard.setReporter(userService.findUserById(dashboardDTO.getReporter()));
        dashboard.setDescription(dashboardDTO.getDescription());
        return null;
    }

    @Override
    public Dashboard findByDashboardById(long dashboardId) {
        return dashboardRepository.findOne(dashboardId);
    }

    @Override
    public List<Dashboard> getAllDashboards(long projectId) {
        List<Dashboard> dashboards = new ArrayList<>();
        dashboardRepository.findAll().forEach(dashboard -> {
            if (dashboard.getProject() == projectService.findByProjectId(projectId)) {
                dashboards.add(dashboard);
            }
        });
        return dashboards;
    }


    @Override
    public List<Dashboard> getAllDashboards() {
        List<Dashboard> dashboards = new ArrayList<>();
        dashboardRepository.findAll().forEach(dashboards::add);
        return dashboards;
    }


    @Override
    public Dashboard changePriority(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setPriority(dashboardPriorityService.findDashboardByPriorityId(dashboardDTO.getPriority()));
        dashboard.setUpdated(dataConverterService.generateTodayStringDay());
        return dashboardRepository.save(dashboard);
    }

    @Override
    public Dashboard changeStatus(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setStatus(dashboardStatusService.findDashboardByStatusId(dashboardDTO.getStatus()));
        dashboard.setUpdated(dataConverterService.generateTodayStringDay());
        return dashboardRepository.save(dashboard);
    }

    @Override
    public Dashboard assignAnotherReporter(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setReporter(userService.findUserById(dashboardDTO.getReporter()));
        dashboard.setUpdated(dataConverterService.generateTodayStringDay());
        return dashboardRepository.save(dashboard);
    }

}
