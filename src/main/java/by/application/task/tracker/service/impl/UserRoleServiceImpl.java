package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.UserRole;
import by.application.task.tracker.repositories.UserRoleRepository;
import by.application.task.tracker.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;

    @Override
    public UserRole findByRoleId(long roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        List<UserRole> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
