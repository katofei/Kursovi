package by.application.task.tracker.service;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(ProjectDTO projectDTO);
    void deleteProject(Long projectId);
    Project editProject(Project project);
    Project findProjectById(Long projectId);
    List<Project> getAllProjects();
}
