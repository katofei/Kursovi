package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
