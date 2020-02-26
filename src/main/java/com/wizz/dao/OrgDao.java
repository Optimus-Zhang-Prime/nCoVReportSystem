package com.wizz.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrgDao {//组织
    String TABLE_NAME = "org";//表名
    String SELECT_FIELDS = "id,name,password";//可查询的字段

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where name=#{name}"})
    User getUserByName(String name);

    @Insert({"insert into ", TABLE_NAME, "(", "name,grade", ") values (#{name},#{grade})"})
    void addOrg(String name, int grade);
}
