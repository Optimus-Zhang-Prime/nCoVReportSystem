package com.wizz.controller;

import com.wizz.service.AdminService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowProjectController {
    @Autowired
    AdminService adminService;

    @ResponseBody//显示项目
    @RequestMapping(path = "user/showproject/", method = RequestMethod.GET)
    public String showProject() throws JSONException {
        return adminService.showProject();
    }
}
