package com.wizz.controller;


import com.wizz.utils.ForumUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//@Controller
//public class CreateOrgController {
//    @Autowired
//    AdminService adminService;
//
//    @ResponseBody//创建组织
//    @RequestMapping(path = "user/createorg/", method = RequestMethod.POST)
//    public String createOrg(@RequestParam("orgName") String orgName, @RequestParam("grade") int grade) throws JSONException {
//        Integer code = adminService.createOrg(orgName, grade);
//        return ForumUtils.toJsonString(code);
//    }
//
//    @ResponseBody//添加组织管理员
//    @RequestMapping(path = "user/addAdmin/", method = RequestMethod.POST)
//    public String addOrgAdmin(@RequestParam("orgid") int orgid,@RequestParam("num") int num,@RequestParam("tel") String tel)  throws JSONException {
//        Integer code = adminService.addAdmin(orgid,num, tel);
//        return ForumUtils.toJsonString(code);
//    }
//}
