package com.wizz.service;

import com.alibaba.fastjson.JSONObject;
import com.wizz.dao.OrgDao;
import com.wizz.dao.ProjectDao;
import com.wizz.dao.UserDao;
import com.wizz.entity.Project;
import com.wizz.entity.User;
import com.wizz.utils.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    OrgDao orgdao;

    public Integer createProject(String projectName) {
        projectDao.addProject(projectName);
        return 1000;//添加项目成功
    }

    public JSONObject showProject(){
        List<Project> allProject=projectDao.getAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allProject", allProject);
        return jsonObject;
    }


    public Integer createOrg(Integer project,String orgName, Integer grade) {
        orgdao.addOrg(project,orgName,grade);
        return 1000;//添加组织成功
    }
    public Integer createOrg(Integer project,String orgName, Integer grade,Integer classA) {
        orgdao.addOrg(project,orgName,grade,classA);
        return 1000;//添加组织成功
    }
    public Integer createOrg(Integer project,String orgName, Integer grade,Integer classA,Integer classB) {
        orgdao.addOrg(project,orgName,grade,classA,classB);
        return 1000;//添加组织成功
    }

    public Integer addAdmin(int orgid,String tel){
        try{
            orgdao.addAdmin(orgid, tel);
            return 1000;
        }
        catch (Exception e){
            return 1006;
        }
    }
}
