package by.application.task.tracker.service;

import by.application.task.tracker.data.Project;

import java.util.List;

public interface ProjectService {

    Project addProject(Project project);
    void deleteProject(Long projectId);
    Project editProject(Project project);
    Project findProjectById(Long projectId);
    List<Project> getAllProjects();
}
