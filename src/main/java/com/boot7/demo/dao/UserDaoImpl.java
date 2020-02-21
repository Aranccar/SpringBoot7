package com.boot7.demo.dao;

import com.boot7.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return  entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getUserByUserName(String username) {
        User user = null;
        Query query = entityManager.createQuery("FROM User where username = :paramName", User.class);
        query.setParameter("paramName", username);
        List result = query.getResultList();
        if(!result.isEmpty()){
           user = (User) result.get(0);
        }
        return user;
    }
}
