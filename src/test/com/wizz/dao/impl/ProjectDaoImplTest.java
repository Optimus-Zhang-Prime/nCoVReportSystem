package com.wizz.dao.impl;

import com.wizz.Application;
import com.wizz.entity.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProjectDaoImplTest {
    @Autowired
    ProjectDaoImpl projectDao;

    @Test
    public void getAll() {
        List<Project> all = projectDao.getAll();
        System.out.println(all);
    }

    @Test
    public void addProject() {
        projectDao.addProject("陕西省");
    }
}