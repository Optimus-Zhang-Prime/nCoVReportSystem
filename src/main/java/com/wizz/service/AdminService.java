package com.wizz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.impl.OrgDaoImpl;
import com.wizz.dao.impl.ProjectDaoImpl;
import com.wizz.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    ProjectDaoImpl projectDao;

    @Autowired
    OrgDaoImpl orgdao;

    public Integer createProject(String projectName) {
        projectDao.addProject(projectName);
        return 1000;//添加项目成功
    }
    public Integer deleteProject(String projectid) {
        try{
        projectDao.deleteProject(projectid);
        projectDao.deleteOrgByProject(projectid);
        return 1000;//删除项目成功
        }
        catch(Exception e){
            return 1006;//删除失败
            }
    }


    public String showProject(){
        List<Project> allProject=projectDao.getAll();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("allProject", allProject);
        String jsonObject = JSON.toJSONString(allProject);
//        System.out.println(jsonObject);
        return jsonObject;
    }


    public Integer createOrg(String project,String orgName, Integer grade) {
        orgdao.addOrg1(project,orgName,grade);
        return 1000;//添加组织成功
    }
    public Integer createOrg(String project,String orgName, Integer grade,String classA) {
        orgdao.addOrg2(project,orgName,grade,classA);
        return 1000;//添加组织成功
    }
    public Integer createOrg(String project,String orgName, Integer grade,String classA,String classB) {
        orgdao.addOrg3(project,orgName,grade,classA,classB);
        return 1000;//添加组织成功
    }

    public Integer addAdmin(String orgid,String tel){
        try{
            orgdao.addAdmin(orgid, tel);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
}
