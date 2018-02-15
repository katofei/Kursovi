package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.DashboardPriority;
import by.application.task.tracker.repositories.DashboardPriorityRepository;
import by.application.task.tracker.service.DashboardPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardPriorityServiceImpl implements DashboardPriorityService {

    @Autowired
    private DashboardPriorityRepository priorityRepository;

    @Override
    public DashboardPriority findDashboardByPriorityId(long dashboardPriorityId) {
        return priorityRepository.findOne(dashboardPriorityId);
    }

    @Override
    public DashboardPriority findDashboardByPriorityName(String dashboardPriorityName) {
        return priorityRepository.findByPriorityName(dashboardPriorityName);
    }

    @Override
    public List<DashboardPriority> getAllDashboardPriorities() {
        List<DashboardPriority> priorities = new ArrayList<>();
        priorityRepository.findAll().forEach(priorities::add);
        return priorities;
    }
}
