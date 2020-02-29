package com.wizz.dao.impl;

import com.wizz.Application;
import com.wizz.entity.Org;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrgDaoImplTest {
    @Autowired
    OrgDaoImpl orgDao;

    @Test
    public void getAdminUser_Org() {
        List<Org> adminUser_org = orgDao.getAdminUser_Org("18630298895");
        System.out.println(adminUser_org);
    }

    @Test
    public void addAdmin() {
        orgDao.addAdmin("32f06985-127c-4874-9a3b-520e4b21625a","1111111111");
    }



    // 异常信息需优化，'此组织不存在'
    @Test
    public void getOrgById() {
        Org orgById = orgDao.getOrgById("32f06985-127e4b21625a");
        System.out.println(orgById);
    }

    @Test
    public void addOrg1() {
        orgDao.addOrg1("12312123","电院",3);
    }

    @Test
    public void addOrg2() {
        orgDao.addOrg2("12312123","电院",3,56);
    }

    @Test
    public void addOrg3() {
        orgDao.addOrg3("12312123","电院",3,23,45);
    }
}