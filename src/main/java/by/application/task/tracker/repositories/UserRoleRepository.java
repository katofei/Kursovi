package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    UserRole findByRoleName(String name);
    UserRole findByRoleId(long id);

}
