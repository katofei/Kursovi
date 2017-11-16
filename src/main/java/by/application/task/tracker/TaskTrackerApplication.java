package by.application.task.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.application.task.tracker.repositories")
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }
    //todo добавить кнопки на edit pages
    //todo добавить на edit pages оставшиеся поля
    //todo сделать ЁБАНЫЙ курсач

}
