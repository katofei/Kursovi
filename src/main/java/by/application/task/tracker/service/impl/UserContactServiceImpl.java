package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.UserContact;
import by.application.task.tracker.repositories.UserContactRepository;
import by.application.task.tracker.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserContactServiceImpl implements UserContactService{

    @Autowired
    private UserContactRepository contactRepository;


    @Override
    public UserContact findByContactId(Long contactId) {
        return contactRepository.findOne(contactId);
    }

    @Override
    public List<UserContact> getAllUserContacts() {
        List<UserContact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }
}
