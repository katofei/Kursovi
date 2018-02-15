package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.DashboardStatus;
import by.application.task.tracker.repositories.DashboardStatusRepository;
import by.application.task.tracker.service.DashboardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardStatusServiceImpl implements DashboardStatusService{

    @Autowired private DashboardStatusRepository statusRepository;

    @Override
    public DashboardStatus findDashboardByStatusId(long dashboardStatusId) {
        return statusRepository.findOne(dashboardStatusId);
    }

    @Override
    public DashboardStatus findDashboardByStatusName(String dashboardStatusName) {
        return statusRepository.findByStatusName(dashboardStatusName);
    }

    @Override
    public List<DashboardStatus> getAllDashboardStatuses() {
        List<DashboardStatus> statuses = new ArrayList<>();
        statusRepository.findAll().forEach(statuses::add);
        return statuses;
    }
}
