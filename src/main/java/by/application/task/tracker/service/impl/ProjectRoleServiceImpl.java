package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.ProjectRole;
import by.application.task.tracker.repositories.ProjectRoleRepository;
import by.application.task.tracker.service.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService {

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @Override
    public ProjectRole findProjectRoleById(long projectRoleId) {
        return projectRoleRepository.findOne(projectRoleId);
    }

    @Override
    public List<ProjectRole> getAllProjectRoles() {
        List<ProjectRole> projectRoles = new ArrayList<>();
        projectRoleRepository.findAll().forEach(projectRoles::add);
        return projectRoles;
    }

    @Override
    public ProjectRole findByProjectRoleName(String projectRoleName) {
        return null;
    }
}
