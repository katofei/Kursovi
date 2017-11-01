package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    User findByUserName(String name);
}
