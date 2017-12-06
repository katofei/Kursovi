package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.UserStatus;

import java.util.List;

public interface UserStatusService {

    UserStatus findByStatusId(Long statusId);
    List<UserStatus> getAllUserStatuses();
    UserStatus findByStatusName(String statusName);
}
