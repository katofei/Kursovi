package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Position findByPositionName(String name);
}
