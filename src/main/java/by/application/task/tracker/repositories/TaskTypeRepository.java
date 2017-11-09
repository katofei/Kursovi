package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.TaskType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends CrudRepository<TaskType, Long> {

    TaskType findByTypeName(String typename);
}
