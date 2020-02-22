package com.wizz.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    String TABLE_NAME = "user";//表名
    String INSERT_FIELDS="name,password";//要填入的字段
    String SELECT_FIELDS="id,name,password";//可查询的字段

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where name=#{name}"})
    User getUserByName(String name);

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values (#{name},#{password})"})
    void addUser(User user);
}
