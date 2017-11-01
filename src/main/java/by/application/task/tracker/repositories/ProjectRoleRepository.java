package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.ProjectRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRoleRepository extends CrudRepository<ProjectRole, Long> {

    ProjectRole findByProjectRoleName(String name);
}
