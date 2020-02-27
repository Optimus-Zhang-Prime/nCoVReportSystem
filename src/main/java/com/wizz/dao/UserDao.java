package com.wizz.dao;


<<<<<<< HEAD
import com.wizz.entity.User;


public interface UserDao {
    String getUserByName(String name);
    void addUser(User user);
=======
@Mapper
@Repository
public interface UserDao {//用户（学生/教职工）
    String TABLE_NAME = "user";//表名

    @Select({"select * from user where _id=#{id}"})
    User getUserById(Integer id);//根据id查找用户

    //@Insert({""})
    //void addUser(User user);//添加用户，方式待定
>>>>>>> 6a53a11f8ba22719f710872a07efe52ae70a959f
}
