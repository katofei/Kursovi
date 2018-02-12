package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Dashboard;
import org.springframework.data.repository.CrudRepository;

public interface DashboardRepository  extends CrudRepository<Dashboard, Long> {

    Dashboard findByDashboardName(String name);
}