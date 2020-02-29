package com.wizz.dao.impl;

import com.wizz.Application;
import com.wizz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserStateDaoImplTest {
    @Autowired
    UserStateDaoImpl userStateDao;

    @Test
    public void getClassAilluser() {
        List<User> classAilluser = userStateDao.getClassAilluser("21431231231231");
        System.out.println(classAilluser);
    }

    @Test
    public void getClassBilluser() {
        List<User> classBilluser = userStateDao.getClassBilluser("21431231231231");
        System.out.println(classBilluser);
    }

    @Test
    public void getClassCilluser() {
        List<User> classCilluser = userStateDao.getClassCilluser("21431231231231");
        System.out.println(classCilluser);
    }

    @Test
    public void getClassAHdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassAHdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassBHdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassBHdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassCHdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassCHdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassAMdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassAMdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassBMdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassBMdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassCMdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassCMdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassALdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassALdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassBLdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassBLdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassCLdangeruser() {
        List<User> classAHdangeruser = userStateDao.getClassCLdangeruser("21431231231231");
        System.out.println(classAHdangeruser);
    }

    @Test
    public void getClassAAllUser() {
        List<User> classAAllUser = userStateDao.getClassAAllUser("21431231231231");
        System.out.println(classAAllUser);
    }

    @Test
    public void getClassBAllUser() {
        List<User> classAAllUser = userStateDao.getClassBAllUser("21431231231231");
        System.out.println(classAAllUser);
    }

    @Test
    public void getClassCAllUser() {
        List<User> classAAllUser = userStateDao.getClassCAllUser("21431231231231");
        System.out.println(classAAllUser);
    }
}