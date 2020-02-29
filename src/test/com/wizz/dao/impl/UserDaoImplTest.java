package com.wizz.dao.impl;

import com.wizz.Application;
import com.wizz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoImplTest {
    @Autowired
    UserDaoImpl userDao;
    @Test
    public void getUserById() {
        User userById = userDao.getUserById("1582966633748_0.3411355827364386_33574851");
        System.out.println(userById);
    }

    @Test
    public void setUserIndex90() {
        userDao.setUserIndex90("213123123");
    }
}