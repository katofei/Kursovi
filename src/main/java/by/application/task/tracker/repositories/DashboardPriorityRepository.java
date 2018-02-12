package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.DashboardPriority;
import org.springframework.data.repository.CrudRepository;

public interface DashboardPriorityRepository  extends CrudRepository<DashboardPriority, Long> {

    DashboardPriority findByPriorityName(String name);
}