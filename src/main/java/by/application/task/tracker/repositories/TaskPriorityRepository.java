package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.TaskPriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskPriorityRepository extends CrudRepository<TaskPriority, Long> {

    TaskPriority findByPriorityName(String name);
}
