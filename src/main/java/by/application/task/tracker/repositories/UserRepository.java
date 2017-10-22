package by.application.task.tracker.repositories;

import by.application.task.tracker.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
}
