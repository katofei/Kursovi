package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.UserContact;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;

import java.util.List;

public interface UserContactService {

    UserContact createContact(UserDTO contactDTO);
    void deleteContact(Long contactId);
    UserContact editContact(UserInfoWrapper userInfoWrapper);
    UserContact findByContactId(Long contactId);
}
