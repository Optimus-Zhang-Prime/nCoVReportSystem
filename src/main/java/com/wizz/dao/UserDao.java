package com.wizz.dao;


import com.wizz.entity.User;



public interface UserDao {
    User getUserById(String id);

    //@Insert({""})
    //void addUser(User user);//添加用户，方式待定


    void setUserIndex90(String id);//将该用户的易感指数设为90（后来改成91了）
    void setUserIndex50(String id);//将该用户的易感指数设为50
    void setUserIndex75(String id);//将该用户的易感指数设为75
}
