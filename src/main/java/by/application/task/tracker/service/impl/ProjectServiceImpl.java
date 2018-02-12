package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.dto.ProjectDTO;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.wrapper.ProjectInfoWrapper;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.repositories.ProjectRepository;
import by.application.task.tracker.service.ProjectContactService;
import by.application.task.tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectContactService contactService;

    @Override
    public Project createProject(ProjectDTO projectDTO) {
        Project createdProject = new Project(projectDTO);
        Date today = new Date();
        LocalDate date  = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        createdProject.setCreated(date.format(DateTimeFormatter.ISO_DATE));
        createdProject.setProjectContact( contactService.createContact(projectDTO));
        if(projectDTO.getDeadLine().isEmpty()){
            createdProject.setDeadLine("Not specified");
        }
        else {
            createdProject.setDeadLine(projectDTO.getDeadLine());
        }
        return projectRepository.save(createdProject);}

    @Override
    public void deleteProject(Long projectId) { projectRepository.delete(projectId);}

    @Override
    public Project editProject(ProjectInfoWrapper projectInfoWrapper) {
        Project project = projectInfoWrapper.getProject();
        project.setProjectContact(projectInfoWrapper.getProjectContact());
        return projectRepository.save(project);
    }

    @Override
    public Project findByProjectId(Long projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }
}
