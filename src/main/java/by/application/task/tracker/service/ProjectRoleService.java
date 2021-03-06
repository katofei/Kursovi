package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.ProjectRole;

import java.util.List;

public interface ProjectRoleService {

    ProjectRole findProjectRoleById(long projectRoleId);
    List<ProjectRole> getAllProjectRoles();
    ProjectRole findByProjectRoleName(String projectRoleName);
}
