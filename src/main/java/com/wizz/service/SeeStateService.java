package com.wizz.service;

import com.wizz.dao.UserDao;
import com.wizz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class SeeStateService {//按组织查看疫情信息
//
//    @Autowired
//    UserStateDao userStateDao;
//
//    public List<User> getIllUser(Integer orggrade, Integer orgid) {
//        if (orggrade == 1) {
//            return userStateDao.getClassAilluser(orgid);//获取一级组织生病的学生
//        } else if (orggrade == 2) {
//            return userStateDao.getClassBilluser(orgid);
//        } else {
//            return userStateDao.getClassCilluser(orgid);
//        }
//
//    }
//
//    public List<User> getHdangerUser(Integer orggrade, Integer orgid) {
//        if (orggrade == 1) {
//            return userStateDao.getClassAHdangeruser(orgid);//获取高度易感用户
//        } else if (orggrade == 2) {
//            return userStateDao.getClassBHdangeruser(orgid);//
//        } else {
//            return userStateDao.getClassCHdangeruser(orgid);
//        }
//
//    }
//
//    public List<User> getMdangerUser(Integer orggrade, Integer orgid) {
//        if (orggrade == 1) {
//            return userStateDao.getClassAMdangeruser(orgid);//获取易感用户
//        }
//        if (orggrade == 2) {
//            return userStateDao.getClassBMdangeruser(orgid);
//        } else {
//            return userStateDao.getClassCMdangeruser(orgid);
//        }
//
//    }
//
//    public List<User> getLdangerUser(Integer orggrade, Integer orgid) {
//        if (orggrade == 1) {
//            return userStateDao.getClassALdangeruser(orgid);//获取无风险用户
//        }
//        if (orggrade == 2) {
//            return userStateDao.getClassBLdangeruser(orgid);
//        } else {
//            return userStateDao.getClassCLdangeruser(orgid);
//        }
//    }
//
//    public List<User> getAllUser(Integer orggrade, Integer orgid) {
//        if (orggrade == 1) {
//            return userStateDao.getClassAAllUser(orgid);//获取一级组织用户
//        }
//        if (orggrade == 2) {
//            return userStateDao.getClassBAllUser(orgid);//二级组织用户
//        } else {
//            return userStateDao.getClassCAllUser(orgid);
//        }
//    }
//}
//


