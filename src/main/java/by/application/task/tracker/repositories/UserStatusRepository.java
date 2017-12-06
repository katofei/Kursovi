package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.UserRole;
import by.application.task.tracker.data.entities.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends CrudRepository<UserRole, Long> {

    UserStatus findByStatusName(String statusName);
    UserStatus findByStatusId(long id);
}