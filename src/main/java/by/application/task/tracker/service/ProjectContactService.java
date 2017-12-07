package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.ProjectContact;

import java.util.List;

public interface ProjectContactService {

    ProjectContact createContact(ProjectDTO contactDTO);
    void deleteContact(Long contactId);
    ProjectContact editContact(ProjectDTO contactDTO, long id);
    ProjectContact findByContactId(Long contactId);
    List<ProjectContact> getAllUserContacts();
}
