package com.wizz.service;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.impl.OrgDaoImpl;
import com.wizz.dao.impl.ReportDaoImpl;
import com.wizz.dao.impl.UserDaoImpl;
import com.wizz.dao.impl.UserStateDaoImpl;
import com.wizz.entity.Org;
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

    public List<User> getIllUser(String orggrandfathername,Integer orggrade, String orgfathername,String orgname) {
        if (orggrade == 1) {
            return userStateDao.getClassAilluser(orgname);//获取一级组织生病的学生
        } else if (orggrade == 2) {
            return userStateDao.getClassBilluser(orgfathername,orgname);
        } else {
            return userStateDao.getClassCilluser(orggrandfathername,orgfathername,orgname);
        }

    }

    public List<User> getHdangerUser(String orggrandfathername,Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAHdangeruser(orgid);//获取高度易感用户
        } else if (orggrade == 2) {
            return userStateDao.getClassBHdangeruser(orgfathername,orgid);//
        } else {
            return userStateDao.getClassCHdangeruser(orggrandfathername,orgfathername,orgid);
        }

    }

    public List<User> getMdangerUser(String orggrandfathername,Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassAMdangeruser(orgid);//获取易感用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBMdangeruser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCMdangeruser(orggrandfathername,orgfathername,orgid);
        }

    }

    public List<User> getLdangerUser(String orggrandfathername,Integer orggrade,String orgfathername, String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassALdangeruser(orgid);//获取无风险用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBLdangeruser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCLdangeruser(orggrandfathername,orgfathername,orgid);
        }
    }
    public List<User> getSuspectedUser(String orggrandfathername,Integer orggrade, String orgfathername,String orgid) {
        if (orggrade == 1) {
            return userStateDao.getClassASuspectedUser(orgid);//获取疑似用户，90-100
        }
        if (orggrade == 2) {
            return userStateDao.getClassBSuspectedUser(orgfathername,orgid);
        } else {
            return userStateDao.getClassCSuspectedUser(orggrandfathername,orgfathername,orgid);
        }
    }



    public List<User> getAllUser(String orggrandfathername,Integer orggrade,String orgfathername, String orgid,Integer page) {
        if (orggrade == 1) {
            return userStateDao.getClassAAllUser(orgid,page);//获取一级组织用户
        }
        if (orggrade == 2) {
            return userStateDao.getClassBAllUser(orgfathername,orgid,page);//二级组织用户
        } else {
            return userStateDao.getClassCAllUser(orggrandfathername,orgfathername,orgid,page);
        }
    }

    public Org getorgByid(String _id){//通过_id查找组织(3
        Org org=orgDao.getorgByid(_id);
        return org;
    }    

    public List<Org> getorgByProject(String projectid){//获取项目下所有组织
        
        List<Org> orgList=orgDao.gerOrgByProjectId(projectid);//组织列表
        for(Org org:orgList){//逐个组织查询用户数量，orgDao.getUserAccount（）参数可添加/修改
            switch (org.getGrade()){
                case 1:
                    org.setNum(orgDao.getUserAccount(org.getName()));
                    break;
                case 2:
                    org.setNum(orgDao.getUserAccount(getorgByid(org.getClassA()).getName(),org.getOrgIdForClassB()));
                    break;
                case 3:
                    org.setNum(orgDao.getUserAccount(getorgByid(org.getClassA()).getName(),getorgByid(org.getClassB()).getName(),org.getName()));
                    break;
            }
        }
        return orgList;
    }

    public List<Org> getorgByParentClass(Integer orggrade,String orgid){//获取组织下所有组织
        List<Org> orgList = new ArrayList<>();
        if(orggrade==1){
            orgList=orgDao.getClassBOrgByParentClass(orgid);
            for(Org org:orgList){//逐个组织查询用户数量，orgDao.getUserAccount（）参数可添加/修改
                org.setNum(orgDao.getUserAccount(org.getName()));
            }
        }
        else if(orggrade==2){
            orgList=orgDao.getClassCOrgByParentClass(orgid);
            for(Org org:orgList){//逐个组织查询用户数量，orgDao.getUserAccount（）参数可添加/修改
                org.setNum(orgDao.getUserAccount(getorgByid(org.getClassA()).getName(),org.getOrgIdForClassB()));
            }
        }
        return orgList;
    }
    //导出组织内用户打卡消息
    // 注意参数中的orgid同样有特殊要求
    public List<ReportsByDate> getReportsByDate (String orggrandfathername,String orgfathername,Integer orggrade, String orgid, Integer month, Integer day) {
        // 使用云函数的方式获得数据
        // 参数：
        // month
        // day
        // orggrade
        // orgid
        // orgfathername
        Map<String,Object> map = new HashMap<>();
        map.put("orggrade",orggrade);
        map.put("orgid",orgid);
        map.put("month",month);
        map.put("day",day);
        map.put("orgfathername",orgfathername);
        map.put("orggrandfathername",orggrandfathername);
        String report = cloudFunctionUtils.InvokeFunction("getSpecificReport", map);
        List lists = JSON.parseObject(report, List.class);
        List<ReportsByDate> reportList= new ArrayList<>();
        for ( Object list: lists) {
            String s = JSON.toJSONString(list);
            ReportsByDate reports = JSON.parseObject(s, ReportsByDate.class);
            if(reports.getSymptom()) {
                reports.setSymptoms("体温高于37.3℃");
            } else {
                reports.setSymptoms("体温低于37.3℃，且无不适症状");
            }
            reports.setCreateTime_str(reports.getCreateTime_str().split("T")[0]);
            reportList.add(reports);
        }
        return reportList;
    }
}


