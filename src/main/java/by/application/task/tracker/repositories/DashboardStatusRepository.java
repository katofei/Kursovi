package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.DashboardStatus;
import org.springframework.data.repository.CrudRepository;

public interface DashboardStatusRepository  extends CrudRepository<DashboardStatus, Long> {

    DashboardStatus findByStatusName(String name);
}