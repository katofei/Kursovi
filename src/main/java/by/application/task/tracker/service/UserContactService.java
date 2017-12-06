package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.UserContact;

import java.util.List;

public interface UserContactService {

    UserContact findByContactId(Long contactId);
    List<UserContact> getAllUserContacts();
}
