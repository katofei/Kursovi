package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.UserContact;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;
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
    public UserContact createContact(UserDTO contactDTO) {
        UserContact contact = new UserContact(contactDTO);
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(long contactId) {
        contactRepository.delete(contactId);
    }

    @Override
    public UserContact editContact(UserInfoWrapper userInfoWrapper) {
        return contactRepository.save(userInfoWrapper.getUserContact());
    }

    @Override
    public UserContact findByContactId(long contactId) {
        return contactRepository.findOne(contactId);
    }

}
