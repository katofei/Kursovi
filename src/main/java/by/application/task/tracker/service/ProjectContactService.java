package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.ProjectContact;

import java.util.List;

public interface ProjectContactService {

    ProjectContact findByContactId(Long contactId);
    List<ProjectContact> getAllUserContacts();
}
