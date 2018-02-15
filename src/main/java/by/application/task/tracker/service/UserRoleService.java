package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole findByRoleId(long roleId);
    List<UserRole> getAllUserRoles();
    UserRole findByRoleName(String roleName);
}
