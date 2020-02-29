package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.service.AdminService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ShowProjectController {
    @Autowired
    AdminService adminService;

    @ResponseBody//显示项目
    @RequestMapping(path = "user/showproject/", method = RequestMethod.POST)
    public JSONObject createProject(@RequestParam("projectid") Integer id) throws JSONException {
        return adminService.showProject();
    }
}
