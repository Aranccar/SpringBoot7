package com.boot7.demo.service;

import com.boot7.demo.dao.UserDao;
import com.boot7.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao usersDAO;

    @Autowired
    public UserServiceImpl(UserDao usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public void addUser(User User) {
        this.usersDAO.addUser(User);
    }

    @Override
    public void updateUser(User User) {
        this.usersDAO.updateUser(User);
    }

    @Override
    public void removeUser(int id) {
        this.usersDAO.removeUser(id);
    }

    @Override
    public User getUserById(int id) {
        return this.usersDAO.getUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return this.usersDAO.listUsers();
    }

    @Override
    public User getUserByUserName(String username) {
        return usersDAO.getUserByUserName(username);
    }
}
