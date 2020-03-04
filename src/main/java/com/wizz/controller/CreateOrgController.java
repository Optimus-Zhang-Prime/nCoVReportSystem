package com.wizz.controller;


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
public class CreateOrgController {
    @Autowired
    AdminService adminService;


    @ResponseBody//创建一级组织
    @RequestMapping(path = "user/createorg1/", method = RequestMethod.POST)
    public String createOrg(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") Integer grade) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade);
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//创建二级组织
    @RequestMapping(path = "user/createorg2/", method = RequestMethod.POST)
    public String createOrg2(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") int grade,@RequestParam("classA")String classA ) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade,classA);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//创建三级组织
    @RequestMapping(path = "user/createorg3/", method = RequestMethod.POST)
    public String createOrg3(@RequestParam("project")String project,@RequestParam("orgName") String orgName, @RequestParam("grade") int grade,@RequestParam("classA")String classA ,@RequestParam("classB") String classB) throws JSONException {
        Integer code = adminService.createOrg(project,orgName, grade,classA,classB);
        return ForumUtils.toJsonString(code);
    }

    @ResponseBody//添加组织管理员
    @RequestMapping(path = "user/addAdmin/", method = RequestMethod.POST)
    public String addOrgAdmin(@RequestParam("orgid") String orgid,@RequestParam("tel") String tel)  throws JSONException {
        Integer code = adminService.addAdmin(orgid, tel);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//删除组织管理员
    @RequestMapping(path = "user/deleteAdmin/", method = RequestMethod.POST)
    public String deleteOrgAdmin(@RequestParam("orgid") String orgid,@RequestParam("tel") String tel)  throws JSONException {
        Integer code = adminService.deleteAdmin(orgid, tel);
        return ForumUtils.toJsonString(code);
    }
    @ResponseBody//删除组织
    @RequestMapping(path = "user/deleteorg/", method = RequestMethod.POST)
    public String deleteOrg(@RequestParam("orgid")String orgid) throws JSONException {
        Integer code = adminService.deleteOrg(orgid);
        return ForumUtils.toJsonString(code);
    }
}
