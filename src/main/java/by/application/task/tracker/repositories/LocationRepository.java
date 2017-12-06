package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByLocationId(long id);
}
