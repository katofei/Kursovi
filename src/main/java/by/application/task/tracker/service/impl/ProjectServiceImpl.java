package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.repositories.ProjectRepository;
import by.application.task.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(ProjectDTO projectDTO) {
        Project createdProject = new Project(projectDTO);
        return projectRepository.save(createdProject);}

    @Override
    public void deleteProject(Long projectId) { projectRepository.delete(projectId);}

    @Override
    public Project editProject(Project project) {return projectRepository.save(project);}

    @Override
    public Project findByProjectId(Long projectId) {return projectRepository.findOne(projectId);}

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }
}
