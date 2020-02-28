package com.wizz.service;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.UserDao;

import com.wizz.dao.impl.ProjectDaoImpl;
import com.wizz.entity.Project;
import com.wizz.entity.User;
import com.wizz.utils.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@Service
//public class AdminService {
//
//    @Autowired
//    ProjectDaoImpl projectDao;
//
//    @Autowired
//    OrgDaoImpl orgdao;
//
//    public Integer createProject(String projectName) {
//        projectDao.addProject(projectName);
//        return 1000;//添加项目成功
//    }
//
//    public JSONObject showProject(){
//        List<Project> allProject=projectDao.getAll();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("allProject", allProject);
//        return jsonObject;
//    }
//
//
//    public Integer createOrg(String orgName, int grade) {
//        orgdao.addOrg(orgName,grade);
//        return 1000;//添加组织成功
//    }
//
//    public Integer addAdmin(int orgid,int num,String tel){
//        try {
//            if (num == 1) {
//                orgdao.addAdmin1(orgid, tel);
//            } else if (num == 2) {
//                orgdao.addAdmin2(orgid, tel);
//            } else if (num == 3) {
//                orgdao.addAdmin3(orgid, tel);
//            }
//            return 1000;
//        }
//        catch (Exception e){
//            return 1006;
//        }
//    }
//}
