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

import javax.servlet.http.HttpServletRequest;


@Controller
public class CreateProjectController {
    @Autowired
    AdminService adminService;

    @ResponseBody//注册
    @RequestMapping(path = "user/createproject/", method = RequestMethod.POST)
    public String createProject(@RequestParam("projectName") String projectName) throws JSONException {
        Integer code = adminService.createProject(projectName);
        return ForumUtils.toJsonString(code);
    }
}