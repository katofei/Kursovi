package by.application.task.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.application.task.tracker.repositories")
public class TaskTrackerApplication {

   @DependsOn(value = "jpaRepositoriesRegistrar")
    @Primary
    public class SpringLiquibase{}


    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }
}
