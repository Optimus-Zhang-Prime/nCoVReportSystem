package com.wizz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wizz.entity.Project;
import com.wizz.service.AdminService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
