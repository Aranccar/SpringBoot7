package com.boot7.demo.dao;

import com.boot7.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleById(int id);

    Role getRoleByName(String name);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(int id);

    List<Role> getRoles();
}
