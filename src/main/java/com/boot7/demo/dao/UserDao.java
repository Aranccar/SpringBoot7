package com.boot7.demo.dao;

import com.boot7.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUsers();

    User getUserByUserName(String userName);
}
