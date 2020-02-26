package com.wizz.dao;


import com.wizz.entity.User;


public interface UserDao {
    String getUserByName(String name);
    void addUser(User user);
}
