package com.wizz.dao;


import com.wizz.entity.User;

import java.util.List;


public interface UserDao {
    User getUserById(String id);

    //@Insert({""})
    //void addUser(User user);//添加用户，方式待定


    void setUserIndex90(String id);//将该用户的易感指数设为90（后来改成91了）
    void setUserIndex50(String id);//将该用户的易感指数设为50
    void setUserIndex75(String id);//将该用户的易感指数设为75
    Integer getUserAccount (); // 获取用户数量
    List<String> getUserid(Integer n);
    void setUserConditionWuFengXian (String uid);
    void setUserConditionYiGan(String uid);
    void setUserConditionGaoduYiGan(String uid);
    void setUserConditionYiSi(String uid);
    void setUserIndex(String id,Integer index); // 设置用户的易感指数
}
