package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.wrapper.ProjectInfoWrapper;
import by.application.task.tracker.repositories.ProjectRepository;
import by.application.task.tracker.service.ProjectContactService;
import by.application.task.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectContactService contactService;
    private final DataConverterService dataConverterService;

    @Autowired
    public ProjectServiceImpl(ProjectContactService contactService, DataConverterService dataConverterService, ProjectRepository projectRepository) {
        this.contactService = contactService;
        this.dataConverterService = dataConverterService;
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(ProjectDTO projectDTO) {
        Project createdProject = new Project(projectDTO);
        createdProject.setCreated(dataConverterService.generateTodayStringDay());
        createdProject.setProjectContact( contactService.createContact(projectDTO));
        if(projectDTO.getDeadLine().isEmpty()){
            createdProject.setDeadLine("Not specified");
        }
        else {
            createdProject.setDeadLine(projectDTO.getDeadLine());
        }
        return projectRepository.save(createdProject);}

    @Override
    public void deleteProject(long projectId) { projectRepository.delete(projectId);}

    @Override
    public Project editProject(ProjectInfoWrapper projectInfoWrapper) {
        Project project = projectInfoWrapper.getProject();
        project.setProjectContact(projectInfoWrapper.getProjectContact());
        return projectRepository.save(project);
    }

    @Override
    public Project findByProjectId(long projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public Project findProjectByProjectName(String projectName) {
        return projectRepository.findProjectByProjectName(projectName);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }
}
