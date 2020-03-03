package com.wizz.service;

import com.wizz.dao.impl.OrgDaoImpl;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.dao.impl.UserStateDaoImpl;
import com.wizz.entity.Org;
import com.wizz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeeStateService {//按组织查看疫情信息

    @Autowired
    UserStateDaoImpl userStateDao;
    @Autowired
    UserDaoImpl userDao;
    @Autowired
    ReportDaoImpl reportDao;
    @Autowired
    OrgDaoImpl orgDao;

    public List<User> getIllUser(Integer orggrade, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAilluser(orgid);//获取一级组织生病的学生
        } else if (orggrade == 2) {
            return userStateDao.getClassBilluser(orgid);
        } else {
            return userStateDao.getClassCilluser(orgid);
        }

    }

    public List<User> getHdangerUser(Integer orggrade, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAHdangeruser(orgid);//获取高度易感用户
        } else if (orggrade == 2) {
            return userStateDao.getClassBHdangeruser(orgid);//
        } else {
            return userStateDao.getClassCHdangeruser(orgid);
        }

    }

    public List<User> getMdangerUser(Integer orggrade, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAMdangeruser(orgid);//获取易感用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBMdangeruser(orgid);
        } else {
            return userStateDao.getClassCMdangeruser(orgid);
        }

    }

    public List<User> getLdangerUser(Integer orggrade, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassALdangeruser(orgid);//获取无风险用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBLdangeruser(orgid);
        } else {
            return userStateDao.getClassCLdangeruser(orgid);
        }
    }
    public List<User> getSuspectedUser(Integer orggrade, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassASuspectedUser(orgid);//获取疑似用户，90-100
        }
        if (orggrade == 2) {
            return userStateDao.getClassBSuspectedUser(orgid);
        } else {
            return userStateDao.getClassCSuspectedUser(orgid);
        }
    }



    public List<User> getAllUser(Integer orggrade, String orgid,Integer page) {
        if (orggrade == 1) {
            return userStateDao.getClassAAllUser(orgid,page);//获取一级组织用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBAllUser(orgid,page);//二级组织用户
        } else {
            return userStateDao.getClassCAllUser(orgid,page);
        }
    }

    public void calculate() {//计算易感系数

        List<String> useridList=reportDao.getStatus123Userid();//获取汇报status为“1/2/3”的用户id
        /*1.有疫区旅居史。2.接触过流行病高发地区（湖北省）人员。3.接触过疑似患者。*/
        for(String uid:useridList){
            userDao.setUserIndex50(uid);//将id=uid的用户易感指数设为50
        }
        List<String> useridList=reportDao.getStatus4Userid();//获取汇报status为“4.接触过确诊患者”的用户id
        for(String uid:useridList){
            userDao.setUserIndex75(uid);//将id=uid的用户易感指数设为75
        }
        List<String> useridList = reportDao.getSymptomUserid();//体温高的用户
        for(String uid:useridList){
            userDao.setUserIndex90(uid);
        }


    }
    public List<Org> getorgByProject(String projectid){//获取项目下所有组织
        List<Org> orgList=orgDao.gerOrgByProjectId(projectid);
        return orgList;
    }
}


