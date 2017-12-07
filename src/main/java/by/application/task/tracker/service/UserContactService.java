package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.UserContact;

import java.util.List;

public interface UserContactService {

    UserContact createContact(UserDTO contactDTO);
    void deleteContact(Long contactId);
    UserContact editContact(UserDTO contactDTO, long id);
    UserContact findByContactId(Long contactId);
    List<UserContact> getAllUserContacts();
}
