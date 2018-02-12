package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.DashboardDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.repositories.DashboardRepository;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Dashboard createDashboard(DashboardDTO dashboardDTO) {
        Dashboard createdDashboard = new Dashboard(dashboardDTO);
        Date today = new Date();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        createdDashboard.setCreated(date.format(DateTimeFormatter.ISO_DATE));
        if (dashboardDTO.getEstimation().isEmpty()) {
            createdDashboard.setEstimation("Not specified");
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
    public void deleteDashboard(Long dashboardId) {
        dashboardRepository.delete(dashboardId);
    }

    @Override
    public Dashboard editDashboard(DashboardDTO dashboardDTO, long dashboardId) {
        Dashboard dashboard = dashboardRepository.findOne(dashboardId);
        Date today = new Date();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dashboard.setUpdated(date.format(DateTimeFormatter.ISO_DATE));
        if (dashboardDTO.getEstimation().isEmpty()) {
            dashboard.setEstimation("Not specified");
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
    public Dashboard findByDashboardById(Long dashboardId) {
        return dashboardRepository.findOne(dashboardId);
    }

    @Override
    public List<Dashboard> getAllDashboards(Long projectId) {
        List<Dashboard> dashboards = new ArrayList<>();
        dashboardRepository.findAll().forEach(dashboard -> {
            if (dashboard.getProject() == projectService.findByProjectId(projectId)) {
                dashboards.add(dashboard);
            }
        });
        return dashboards;
    }

    @Override
    public Dashboard changePriority(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setPriority(dashboardPriorityService.findDashboardByPriorityId(dashboardDTO.getPriority()));
        Date today = new Date();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dashboard.setUpdated(date.format(DateTimeFormatter.ISO_DATE));
        return dashboardRepository.save(dashboard);
    }

    @Override
    public Dashboard changeStatus(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setStatus(dashboardStatusService.findDashboardByStatusId(dashboardDTO.getStatus()));
        Date today = new Date();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dashboard.setUpdated(date.format(DateTimeFormatter.ISO_DATE));
        return dashboardRepository.save(dashboard);
    }

    @Override
    public Dashboard assignAnotherReporter(DashboardDTO dashboardDTO, long id) {
        Dashboard dashboard = dashboardRepository.findOne(id);
        dashboard.setReporter(userService.findUserById(dashboardDTO.getReporter()));
        Date today = new Date();
        LocalDate date = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dashboard.setUpdated(date.format(DateTimeFormatter.ISO_DATE));
        return dashboardRepository.save(dashboard);
    }

}
