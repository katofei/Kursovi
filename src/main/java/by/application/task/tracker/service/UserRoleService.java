package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole findByRoleId(Long roleId);
    List<UserRole> getAllUserRoles();
    UserRole findByRoleName(String roleName);
}
