package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.ProjectContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectContactRepository extends CrudRepository<ProjectContact, Long> {
    ProjectContact findByContactId(long id);
}