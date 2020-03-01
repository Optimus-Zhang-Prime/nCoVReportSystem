package com.wizz.dao;

import com.wizz.entity.Project;
import org.springframework.stereotype.Repository;
import java.util.List;



public interface ProjectDao {
    List<Project> getAll();//返回所有项目
    void addProject(String name);//添加项目
}
