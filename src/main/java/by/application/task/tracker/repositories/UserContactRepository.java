package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.UserContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends CrudRepository<UserContact, Long> {
    UserContact findByContactId(long id);
    UserContact findByWorkEmail(String workEmail);
}