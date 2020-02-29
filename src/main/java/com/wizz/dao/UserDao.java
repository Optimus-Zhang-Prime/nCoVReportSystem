package com.wizz.dao;


import com.wizz.entity.User;



public interface UserDao {
    User getUserById(String id);

    //@Insert({""})
    //void addUser(User user);//添加用户，方式待定


    void setUserIndex90(String id);//将该用户的易感指数设为90
}
