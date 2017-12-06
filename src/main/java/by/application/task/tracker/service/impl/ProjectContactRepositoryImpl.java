package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.ProjectContact;
import by.application.task.tracker.repositories.ProjectContactRepository;
import by.application.task.tracker.service.ProjectContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectContactRepositoryImpl implements ProjectContactService{

    @Autowired
    private ProjectContactRepository contactRepository;

    @Override
    public ProjectContact findByContactId(Long contactId) {
        return contactRepository.findOne(contactId);
    }

    @Override
    public List<ProjectContact> getAllUserContacts() {
        List<ProjectContact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }
}
