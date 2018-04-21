package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.entities.UserLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends CrudRepository<UserLog, Long> {

    UserLog findByExecutor(User executor);
}
