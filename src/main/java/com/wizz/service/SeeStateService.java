package com.wizz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.impl.OrgDaoImpl;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.dao.impl.UserStateDaoImpl;
import com.wizz.entity.Org;
import com.wizz.entity.Report;
import com.wizz.entity.User;
import com.wizz.entity.jsonReturn.ReportsByDate;
import com.wizz.utils.CloudFunctionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CloudFunctionUtils cloudFunctionUtils;

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

    public List<Org> getorgByProject(String projectid){//获取项目下所有组织
        List<Org> orgList=orgDao.gerOrgByProjectId(projectid);
        return orgList;
    }

    public List<Org> getorgByParentClass(Integer orggrade,String orgid){//获取项目下所有组织
        List<Org> orgList = new ArrayList<>();
        if(orggrade==1){
            orgList=orgDao.getClassBOrgByParentClass(orgid);
        }
        else if(orggrade==2){
            orgList=orgDao.getClassCOrgByParentClass(orgid);
        }
        return orgList;
    }
    //导出组织内用户打卡消息
    // 注意参数中的orgid同样有特殊要求
    public List<ReportsByDate> getReportsByDate (Integer orggrade, String orgid, String month, String day) {
        // 使用云函数的方式获得数据
        // 参数：
        // month
        // day
        // orggrade
        // orgid
        Map<String,Object> map = new HashMap<>();
        map.put("orggrade",orggrade);
        map.put("orgid",orgid);
        map.put("month",month);
        map.put("day",day);
        String report = cloudFunctionUtils.InvokeFunction("getSpecificReport", map);
        List lists = JSON.parseObject(report, List.class);
        List<ReportsByDate> reportList= new ArrayList<>();
        for ( Object list: lists) {
            String s = JSON.toJSONString(list);
            ReportsByDate reports = JSON.parseObject(s, ReportsByDate.class);
            reportList.add(reports);
        }
        return reportList;
    }
}


