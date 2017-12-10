package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.wrapper.ProjectInfoWrapper;
import by.application.task.tracker.data.entities.ProjectContact;

import java.util.List;

public interface ProjectContactService {

    ProjectContact createContact(ProjectDTO contactDTO);
    void deleteContact(Long contactId);
    ProjectContact editContact(ProjectInfoWrapper projectInfoWrapper);
    ProjectContact findByContactId(Long contactId);
    List<ProjectContact> getAllUserContacts();
}
