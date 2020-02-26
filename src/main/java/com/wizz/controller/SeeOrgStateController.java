package com.wizz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wizz.service.AdminService;
import com.wizz.service.SeeStateService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class SeeOrgStateController {//按组织查看疫情信息
    @Autowired
    SeeStateService seeStateService;

    @ResponseBody
    @RequestMapping(path = "user/seeorgstate/", method = RequestMethod.POST)
    public JSONObject createProject(@RequestParam("orgid") Integer orgid) throws JSONException {
        Integer illAccount=seeStateService.getIllAccount(orgid);//确诊人数
    }
}