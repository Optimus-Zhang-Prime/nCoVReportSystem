package com.wizz.controller;

import com.wizz.entity.Org;
import com.wizz.service.AdminService;
import com.wizz.utils.ForumUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CreateProjectController {
    @Autowired
    AdminService adminService;
    @Autowired
    SeeStateService seeStateService;

    @ResponseBody//注册
    @RequestMapping(path = "user/createproject/", method = RequestMethod.POST)
    public String createProject(@RequestParam("projectName") String projectName) throws JSONException {
        Integer code = adminService.createProject(projectName);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//删除项目
    @RequestMapping(path = "user/deleteproject/", method = RequestMethod.POST)
    public String deleteProject(@RequestParam("projectid") String projectid) throws JSONException {
        Integer code = adminService.deleteProject(projectid);
        List<Org> orglist=seeStateService.getorgByProject(String projectid);//获取项目下所有组织
        for(Org org:orglist){//逐个删除
            String aid=org.get_id;
            Integer code2 = adminService.deleteOrg(aid);
        }
        return ForumUtils.toJsonString(code);
    }

}

