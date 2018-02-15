package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.wrapper.ProjectInfoWrapper;
import by.application.task.tracker.data.entities.ProjectContact;
import by.application.task.tracker.repositories.ProjectContactRepository;
import by.application.task.tracker.service.ProjectContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectContactRepositoryImpl implements ProjectContactService {

    @Autowired
    private ProjectContactRepository projectContactRepository;


    @Override
    public ProjectContact createContact(ProjectDTO contactDTO) {
        ProjectContact contact = new ProjectContact(contactDTO);
        return projectContactRepository.save(contact);
    }

    @Override
    public void deleteContact(long contactId) {
        projectContactRepository.delete(contactId);
    }

    @Override
    public ProjectContact editContact(ProjectInfoWrapper projectInfoWrapper) {
        return projectContactRepository.save(projectInfoWrapper.getProjectContact());

    }

    @Override
    public ProjectContact findByContactId(long contactId) {
        return projectContactRepository.findOne(contactId);
    }

    @Override
    public List<ProjectContact> getAllUserContacts() {
        List<ProjectContact> contacts = new ArrayList<>();
        projectContactRepository.findAll().forEach(contacts::add);
        return contacts;
    }
}
