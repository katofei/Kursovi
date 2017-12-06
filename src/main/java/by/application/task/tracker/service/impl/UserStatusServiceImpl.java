package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.UserStatus;
import by.application.task.tracker.repositories.UserStatusRepository;
import by.application.task.tracker.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    private UserStatusRepository statusRepository;


    @Override
    public UserStatus findByStatusId(Long statusId) {
        return statusRepository.findByStatusId(statusId);
    }

    @Override
    public List<UserStatus> getAllUserStatuses() {
        List<UserStatus> statuses = new ArrayList<>();
        statusRepository.findAll().forEach(statuses::add);
        return statuses;
    }

    @Override
    public UserStatus findByStatusName(String statusName) {
        return statusRepository.findByStatusName(statusName);
    }
}
