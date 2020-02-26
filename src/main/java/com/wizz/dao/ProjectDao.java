package com.wizz.dao;

import com.wizz.entity.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDao {
    String TABLE_NAME = "project";//表名

    @Select({"select * " ,"from", TABLE_NAME})
    List<Project> getAll();

    @Insert({"insert into ", TABLE_NAME, "(", "name", ") values (#{name})"})
    void addProject(String name);
}
