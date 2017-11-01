package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.TaskStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends CrudRepository<TaskStatus, Long> {

    TaskStatus findByStatusName(String name);
}
