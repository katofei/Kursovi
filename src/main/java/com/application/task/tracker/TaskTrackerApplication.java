package com.application.task.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.application.task_tracker.repositories")
public class TaskTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerApplication.class, args);
	}
}
