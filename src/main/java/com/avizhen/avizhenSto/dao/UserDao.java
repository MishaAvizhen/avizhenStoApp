package com.avizhen.avizhenSto.dao;

import com.avizhen.avizhenSto.entity.User;

import java.util.List;

/**
 * Created by Александр on 05.11.2020.
 */
public interface UserDao {

    void saveUser(User  user);

    List<User> findAllUsers();

    void deleteUserById(int id);

    User findById(int id);

    void updateUser(User user);
}
