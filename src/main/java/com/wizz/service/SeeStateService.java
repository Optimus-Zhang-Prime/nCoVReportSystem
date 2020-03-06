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

    public List<User> getIllUser(Integer orggrade, String orgfathername,String orgname) {
        if (orggrade == 1) {
            return userStateDao.getClassAilluser(orgfathername,orgname);//获取一级组织生病的学生
        } else if (orggrade == 2) {
            return userStateDao.getClassBilluser(orgfathername,orgname);
        } else {
            return userStateDao.getClassCilluser(orgfathername,orgname);
        }

    }

    public List<User> getHdangerUser(Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAHdangeruser(orgfathername,orgid);//获取高度易感用户
        } else if (orggrade == 2) {
            return userStateDao.getClassBHdangeruser(orgfathername,orgid);//
        } else {
            return userStateDao.getClassCHdangeruser(orgfathername,orgid);
        }

    }

    public List<User> getMdangerUser(Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAMdangeruser(orgfathername,orgid);//获取易感用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBMdangeruser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCMdangeruser(orgfathername,orgid);
        }

    }

    public List<User> getLdangerUser(Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassALdangeruser(orgfathername,orgid);//获取无风险用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBLdangeruser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCLdangeruser(orgfathername,orgid);
        }
    }
    public List<User> getSuspectedUser(Integer orggrade, String orgfathername,String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassASuspectedUser(orgfathername,orgid);//获取疑似用户，90-100
        }
        if (orggrade == 2) {
            return userStateDao.getClassBSuspectedUser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCSuspectedUser(orgfathername,orgid);
        }
    }



    public List<User> getAllUser(Integer orggrade,String orgfathername, String orgid,Integer page) {
        if (orggrade == 1) {
            return userStateDao.getClassAAllUser(orgfathername,orgid,page);//获取一级组织用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBAllUser(orgfathername,orgid,page);//二级组织用户
        } else {
            return userStateDao.getClassCAllUser(orgfathername,orgid,page);
        }
    }

    public List<Org> getorgByProject(String projectid){//获取项目下所有组织
        List<Org> orgList=orgDao.gerOrgByProjectId(projectid);
        return orgList;
    }

    public List<Org> getorgByParentClass(Integer orggrade,String orgfathername,String orgid){//获取项目下所有组织
        List<Org> orgList = new ArrayList<>();
        if(orggrade==1){
            orgList=orgDao.getClassBOrgByParentClass(orgfathername,orgid);
        }
        else if(orggrade==2){
            orgList=orgDao.getClassCOrgByParentClass(orgfathername,orgid);
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


