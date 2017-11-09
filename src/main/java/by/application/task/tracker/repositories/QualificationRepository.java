package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {

    Qualification findByQualificationName(String name);
}
