package com.boot7.demo.dao;

import com.boot7.demo.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String username) {
        Role role = null;
        Query query = entityManager.createQuery("FROM Role where role = :paramName", Role.class);
        query.setParameter("paramName", username);
        List result = query.getResultList();
        if(!result.isEmpty()){
            role = (Role) result.get(0);
        }
        return role;

    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        Role result = getRoleById(role.getId());
        result.setRole(role.getRole());
        entityManager.flush();
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }
}
