package com.wizz.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {//用户（学生/教职工）
    String TABLE_NAME = "user";//表名

    @Select({"select * from user where _id=#{id}"})
    User getUserById(Integer id);//根据id查找用户

    //@Insert({""})
    //void addUser(User user);//添加用户，方式待定

    @Update({"update User set index=90 where id=#{id}"})
    void setUserIndex90(Integer id);//将该用户的易感指数设为90
}
